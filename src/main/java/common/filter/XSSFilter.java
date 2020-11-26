package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
public class XSSFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XSSFilterRequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		this.config = null;
	}

}