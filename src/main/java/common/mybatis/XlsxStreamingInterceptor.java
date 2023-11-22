package common.mybatis;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.utils.SecurityUtils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 *
 * 엑셀 파일로 목록 데이터 조회 시 List 객체에 담지않고 엑셀파일(xlsx)을 바로 생성(대용량 데이터 조회 시 Heap Memory Full 방지).
 * 생성된 엑셀파일은 XlsxStreamingView에서 브라우저로 전송 함.
 */
@Component
@Order(101)
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
                BoundSql.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
public class XlsxStreamingInterceptor implements Interceptor {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();

		String uri = request.getRequestURI();
		String actionName = uri.substring(uri.lastIndexOf("/") + 1);

		ResultHandler beforeResultHandler = (ResultHandler)args[3];

		if (actionName.endsWith(".xlsx")) {
			HttpServletResponse response = servletRequestAttributes.getResponse();

			Cookie[] cookies = request.getCookies();

			boolean isStreaming = false;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("n-excel-stream".equals(cookie.getName())) {
						String isStreamingStr = new String(Base64.getDecoder().decode(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8).replaceAll(" ", "+")), "UTF-8");
						isStreaming = "true".equals(isStreamingStr);

						// Remove Cookie
						cookie.setValue("");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						Objects.requireNonNull(response).addCookie(cookie);
					}
				}
			}
			if(!isStreaming) {
				return invocation.proceed();
			}

			// Excel Streaming Request
			String fileName = null;
			String columnNamesStr = "";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("n-excel-filename".equals(cookie.getName())) {
						fileName = URLDecoder.decode(new String(Base64.getDecoder().decode(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8).replaceAll(" ", "+")), "UTF-8"), "UTF-8");

						// Remove Cookie
						cookie.setValue("");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					} else if (cookie.getName().startsWith("n-excel-column-names")) {
						columnNamesStr += cookie.getValue();

						// Remove Cookie
						cookie.setValue("");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}

			if (fileName == null) {
				if (logger.isWarnEnabled()) {
					logger.warn("No excel file name was sent from the UI.");
				}
				fileName = "NOFILENAME";
			}
			if (columnNamesStr.isEmpty()) {
				if (logger.isWarnEnabled()) {
					logger.warn("No column name was sent from the UI.");
				}
				columnNamesStr = "{}";
			} else {
				columnNamesStr = URLDecoder.decode(new String(Base64.getDecoder().decode(URLDecoder.decode(columnNamesStr, StandardCharsets.UTF_8).replaceAll(" ", "+")), "UTF-8"), "UTF-8");
			}


			Map<String, Object> xlsxFileInfo = new HashMap<String, Object>();
			xlsxFileInfo.put("downloadFileName", fileName);
			xlsxFileInfo.put("streaming", isStreaming);

			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> columnNames = (Map<String, Object>) mapper.readValue(columnNamesStr.getBytes(), Map.class);

			SXSSFWorkbook workbook = new SXSSFWorkbook(1000);

			Sheet worksheet = workbook.createSheet(fileName);
			worksheet.setDefaultColumnWidth(12);

			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setAlignment(HorizontalAlignment.CENTER);
			headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			headerStyle.setBorderBottom(BorderStyle.THIN);

			args[3] = new ResultHandler<Object>() {
				Row row = null;
				int rowIdx = 0;

				@Override
				public void handleResult(ResultContext<? extends Object> context) {
					// execute ResultHandler of BeforeInterceptor
					if(beforeResultHandler != null) {
						beforeResultHandler.handleResult(context);
					}

					Object obj = context.getResultObject();

					if (obj instanceof Map) {
						@SuppressWarnings("unchecked")
						Map<String, Object> vo = (Map<String, Object>) obj;

						// Header
						int colIdx;
						if (rowIdx == 0) {
							row = worksheet.createRow(rowIdx);
							colIdx = 0;
							for (String key : vo.keySet()) {
								Cell cell = row.createCell(colIdx, CellType.STRING);
								cell.setCellValue(columnNames != null && columnNames.get(key) != null ? (String) columnNames.get(key) : key);
								cell.setCellStyle(headerStyle);
								colIdx++;
							}
						}

						// Body
						rowIdx++;
						row = worksheet.createRow(rowIdx);
						colIdx = 0;
						for (String key : vo.keySet()) {
							if (vo.get(key) instanceof Integer) {
								row.createCell(colIdx, CellType.NUMERIC).setCellValue((Integer) vo.get(key));
							} else if (vo.get(key) instanceof Long) {
								row.createCell(colIdx, CellType.NUMERIC).setCellValue((Long) vo.get(key));
							} else if (vo.get(key) instanceof Float) {
								row.createCell(colIdx, CellType.NUMERIC).setCellValue((Float) vo.get(key));
							} else if (vo.get(key) instanceof Double) {
								row.createCell(colIdx, CellType.NUMERIC).setCellValue((Double) vo.get(key));
							} else if (vo.get(key) instanceof BigDecimal) {
								row.createCell(colIdx, CellType.NUMERIC).setCellValue(((BigDecimal) vo.get(key)).doubleValue());
							} else if (vo.get(key) instanceof Boolean) {
								row.createCell(colIdx, CellType.BOOLEAN).setCellValue((Boolean) vo.get(key));
							} else if (vo.get(key) instanceof Date) {
								row.createCell(colIdx).setCellValue((Date) vo.get(key));
							} else if (vo.get(key) instanceof java.sql.Date) {
								row.createCell(colIdx).setCellValue((java.sql.Date) vo.get(key));
							} else if (vo.get(key) instanceof Time) {
								row.createCell(colIdx).setCellValue((Time) vo.get(key));
							} else if (vo.get(key) instanceof Timestamp) {
								row.createCell(colIdx).setCellValue((Timestamp) vo.get(key));
							} else {
								if (vo.get(key) != null && vo.get(key) instanceof String) {
									row.createCell(colIdx, CellType.STRING).setCellValue(SecurityUtils.reverseXSSString((String) vo.get(key)));
								} else {
									row.createCell(colIdx, CellType.STRING).setCellValue(Objects.toString(vo.get(key), ""));
								}
							}
							colIdx++;
						}

						vo.clear();

					}

				}

			};

			invocation.proceed();

			OutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				File tempFile = File.createTempFile("xlsx_", ".tmp");
				xlsxFileInfo.put("xlsxTempFile", tempFile);
				tempFile.deleteOnExit();

				os = new FileOutputStream(tempFile);
				bos = new BufferedOutputStream(os);

				workbook.write(bos);
				bos.flush();
			} catch(IOException ioe) {
				if(bos != null) {
					bos.close();
				}
				if(os != null) {
					os.close();
				}
			} finally {
				if(bos != null) {
					bos.close();
				}
				if(os != null) {
					os.close();
				}
			}

			// Closeable only implemented as of POI 3.10
            ((Closeable) workbook).close();

            // Dispose of temporary files in case of streaming variant...
			((SXSSFWorkbook) workbook).dispose();

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(xlsxFileInfo);
			return list;
		} else {
			return invocation.proceed();
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
