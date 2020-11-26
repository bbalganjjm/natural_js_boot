package common.utils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

public class SecurityUtils {

	private static String[][] xssChars = {
	        { "&", "&amp;" },
	        { "\\/", "&#x2F;" },
	        { "<", "&lt;" },
	        { ">", "&gt;" },
	        { "'", "&#x27;" },
	        { "\\\"", "&quot;" }
	};

	/**
	 * XSS 공격 문자 치환
	 *
	 * @param value
	 * @return
	 */
	public static String cleanXSSString(String value) {
		for (int i = 0; i < xssChars.length; i++) {
			value = value.replaceAll(xssChars[i][0], xssChars[i][1]);
		}
		return value;
	}

	/**
	 * XSS 공격 문자를 치환 문자를 원래 문자로.
	 *
	 * @param value
	 * @return
	 */
	public static String reverseXSSString(String value) {
		for (int i = 0; i < xssChars.length; i++) {
			value = value.replaceAll(xssChars[i][1], xssChars[i][0].replaceAll("\\\\", ""));
		}
		return value;
	}
}
