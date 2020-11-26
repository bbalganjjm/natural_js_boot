package common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import common.utils.SecurityUtils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
public final class XSSFilterRequestWrapper extends HttpServletRequestWrapper {

	public XSSFilterRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = SecurityUtils.cleanXSSString(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return SecurityUtils.cleanXSSString(value);
	}

	@Override
	public String getHeader(String name) {
	    if("Content-Type".equals(name) || "Accept".equals(name)) {
	        return super.getHeader(name);
	    }

		String value = super.getHeader(name);
		if (value == null)
			return null;
		return SecurityUtils.cleanXSSString(value);
	}

}