package common.mybatis;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import common.exception.CommonException;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
@Component
@Order(103)
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class MaxRowsLimitInterceptor implements Interceptor {

    @Value("${max.rows.exclude.urls}")
    private String[] maxRowsExcludeUrls;

    @Value("${max.rows.limit}")
    private int maxRowsLimit;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		boolean isExcludeUrl = false;
		String uri = request.getRequestURI();
		String actionName = uri.substring(uri.lastIndexOf("/") + 1);
		if (!actionName.startsWith("get")) {
			isExcludeUrl = true;
		}

		for (int i = 0; i < maxRowsExcludeUrls.length; i++) {
			AntPathMatcher m = new AntPathMatcher();
			if (m.match(maxRowsExcludeUrls[i], uri)) {
				isExcludeUrl = true;
				break;
			}
		}

		// Excel 조회일때는 그냥 통과
		if (isExcludeUrl || actionName.endsWith(".xlsx")) {
			return invocation.proceed();
		}

		if (maxRowsLimit == 0) {
			return invocation.proceed();
		}

		((Statement) args[0]).setMaxRows(maxRowsLimit + 1);

		Object actual = invocation.proceed();

		if (actual != null && actual instanceof List) {
			if (((List<?>) actual).size() > maxRowsLimit || (request.getAttribute("rowCnt") != null && (int) request.getAttribute("rowCnt") > maxRowsLimit)) {
				throw new CommonException(-23, new String[] { String.valueOf(maxRowsLimit) });
			}
		}

		return actual;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
