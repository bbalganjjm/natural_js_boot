package common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
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