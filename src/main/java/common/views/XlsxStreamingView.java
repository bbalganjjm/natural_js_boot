package common.views;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.exception.CommonException;
import common.utils.SecurityUtils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

public class XlsxStreamingView extends AbstractXlsxStreamingView {
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response)
	        throws JsonParseException, JsonMappingException, IOException, InvalidFormatException, CommonException {
		
		if (model.get("error") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> eMsg = (Map<String, Object>) model.get("error");
			if (eMsg.get("code") != null && eMsg.get("message") == null) {
				throw new CommonException((Integer) eMsg.get("code"));
			} else if (eMsg.get("code") != null && eMsg.get("message") != null) {
				throw new CommonException((Integer) eMsg.get("code"), (String) eMsg.get("message"));
			}
		}

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> voList = (List<Map<String, Object>>) model.get("mapList");

		if (voList != null) {
			Map<String, Object> xlsxFileInfo = null;

			boolean isStreaming = false;

			File xlsxTempFile = null;
			String fileName = null;
			if (voList != null && voList.size() == 1) {
				xlsxFileInfo = voList.get(0);
				if (xlsxFileInfo != null && xlsxFileInfo.get("streaming") != null) {
					isStreaming = ((Boolean) xlsxFileInfo.get("streaming")).booleanValue();
					fileName = (String) xlsxFileInfo.get("downloadFileName");
					xlsxTempFile = (File) xlsxFileInfo.get("xlsxTempFile");
				}
			}

			if (isStreaming) {
				if (xlsxTempFile != null) {
					buildFromFile(request, response, xlsxTempFile, fileName);
				} else {
					throw new CommonException(-22);
				}
			} else {
				buildFromObject(model, workbook, request, response, voList);
			}
		} else {
			throw new CommonException(-21);
		}
	}

	private void buildFromObject(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response,
	        List<Map<String, Object>> voList) throws JsonParseException, JsonMappingException, IOException {
		Cookie[] cookies = request.getCookies();
		String fileName = null;
		String columnNamesStr = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("n-excel-filename".equals(cookie.getName())) {
					fileName = URLDecoder.decode(new String(Base64Utils.decodeFromString(URLDecoder.decode(cookie.getValue(), "UTF-8").replaceAll(" ", "+")), "UTF-8"), "UTF-8");

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
		if (columnNamesStr.equals("")) {
			if (logger.isWarnEnabled()) {
				logger.warn("No column name was sent from the UI.");
			}
			columnNamesStr = "{}";
		} else {
			columnNamesStr = URLDecoder.decode(new String(Base64Utils.decodeFromString(URLDecoder.decode(columnNamesStr, "UTF-8").replaceAll(" ", "+")), "UTF-8"), "UTF-8");
		}

		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> columnNames = (Map<String, Object>) mapper.readValue(columnNamesStr.getBytes(), Map.class);

		Sheet worksheet = workbook.createSheet(fileName);
		worksheet.setDefaultColumnWidth(12);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setBorderBottom(BorderStyle.THIN);

		Row row = null;
		int rowIdx = 0;
		for (Map<String, Object> vo : voList) {
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

			/*
			 * Column Auto Resize. if (rowIdx == voList.size() - 1) { for (int colNum = 0;
			 * colNum < vo.keySet().size(); colNum++) { worksheet.autoSizeColumn(colNum); }
			 * }
			 */
		}

		setResponseHeader(request, response, fileName);
	}

	private void buildFromFile(HttpServletRequest request, HttpServletResponse response, File xlsxTempFile, String fileName) throws IOException {
		setResponseHeader(request, response, fileName);

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			FileUtils.copyFile(xlsxTempFile, out);
			out.flush();
		} catch (IOException e) {
			if (out != null) {
				out.close();
			}
			xlsxTempFile.delete();
		} finally {
			if (out != null) {
				out.close();
			}
			if(!xlsxTempFile.delete()) {
				if(logger.isWarnEnabled()) {
					logger.warn(messageSource.getMessage("-31", new String[] { xlsxTempFile.getName() }, Locale.getDefault()));
				}
			}
		}
	}

	private void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
		if (request.getHeader("User-Agent").indexOf("Trident") > -1 || request.getHeader("User-Agent").indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20") + ".xlsx";
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx";
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}
}