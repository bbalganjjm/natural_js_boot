package common.mybatis;

import java.lang.invoke.MethodHandles;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.utils.MaskingUtils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
                BoundSql.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
public class MaskingInterceptor implements Interceptor {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${masking.exclude.urls}")
    private String[] maskingExcludeUrls;

	@Value("${masking.columns}")
    private String[] maskingColumns;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();

		boolean isExcludeUrl = false;
		String uri = request.getRequestURI();
		String actionName = uri.substring(uri.lastIndexOf("/") + 1);
		if (actionName.startsWith("get")) {
			isExcludeUrl = true;
		}

		for (int i = 0; i < maskingExcludeUrls.length; i++) {
			AntPathMatcher m = new AntPathMatcher();
			if (m.match(maskingExcludeUrls[i], uri)) {
				isExcludeUrl = false;
				break;
			}
		}

		ResultHandler beforeResultHandler = (ResultHandler)args[3];

		if (isExcludeUrl && maskingColumns != null && maskingColumns.length > 0) {
			boolean[] isStreaming = { false };
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("n-excel-stream".equals(cookie.getName())) {
						String isStreamingStr = new String(Base64Utils.decodeFromString(URLDecoder.decode(cookie.getValue(), "UTF-8").replaceAll(" ", "+")), "UTF-8");
						isStreaming[0] = isStreamingStr != null && "true".equals(isStreamingStr);
					}
				}
			}

			List<Object> list = new ArrayList<Object>();

			args[3] = new ResultHandler<Object>() {

				@Override
				public void handleResult(ResultContext<? extends Object> context) {
					// excute ResultHandler of BeforeInterceptor
					if(beforeResultHandler != null) {
						beforeResultHandler.handleResult(context);
					}

					Object obj = context.getResultObject();

					if (obj instanceof Map) {
						@SuppressWarnings("unchecked")
						Map<String, Object> vo = (Map<String, Object>) obj;

						for (int i = 0; i < maskingColumns.length; i++) {
							String[] maskingInfo = StringUtils.trim(maskingColumns[i]).split("\\|");

							if (maskingInfo.length == 2) {
								String columnName = StringUtils.trim(maskingInfo[0]);
								String maskingRule = StringUtils.trim(maskingInfo[1]);

								if (vo.containsKey(columnName) && vo.get(columnName) instanceof String) {
									String maskedStr = MaskingUtils.maskString(maskingRule, (String) vo.get(columnName));

									/*
									if (logger.isInfoEnabled()) {
										logger.info("The \"" + columnName + "\" column value is masked from \"" + (String) vo.get(columnName) + "\" to \"" + maskedStr + "\".");
									}
									*/

									vo.put(columnName, maskedStr);
								}
							}
						}
					}

					if (!isStreaming[0]) {
						list.add(obj);
						request.setAttribute("rowCnt", context.getResultCount());
					}
				}

			};

			if (!isStreaming[0]) {
				invocation.proceed();
				return list;
			} else {
				return invocation.proceed();
			}

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
