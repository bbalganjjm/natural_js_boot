package common.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
public class MaskingUtils {

	public static String maskString(String rule, String str) {
		return maskString(rule, str, '*');
	}
	
	public static String maskString(String rule, String str, char mc) {
		switch (rule) {
		case "name":
			return name(str, mc);
		case "rrn":
			return rrn(str, mc);
		case "frn":
			return frn(str, mc);
		case "pn":
			return pn(str, mc);
		case "dln":
			return dln(str, mc);
		case "phone":
			return phone(str, mc);
		case "email":
			return email(str, mc);
		case "card":
			return card(str, mc);
		case "an":
			return an(str, mc);
		case "address":
			return address(str, mc);
		case "ip":
			return ip(str, mc);
		default:
			return str;
		}
	}

	/**
	 * 성명
	 * 
	 * @param str
	 * @return
	 */
	public static String name(String str, char mc) {
		String rStr = str;

		String pattern = "";
		if (str.length() == 2) {
			pattern = "^(.)(.+)$";
		} else {
			if(str.indexOf(" ") > -1) {
				pattern = "^(.{3})(.+)(.{3})$";
			} else {
				pattern = "^(.)(.+)(.)$";
			}
		}

		Matcher matcher = Pattern.compile(pattern).matcher(str);

		if (matcher.matches()) {
			rStr = "";

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 2) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}

			}
		}

		return rStr;
	}
	
	/**
	 * 주민등록번호
	 * 
	 * @param str
	 * @return
	 */
	public static String rrn(String str, char mc) {
		int length = 6;
		char[] c = new char[length];
		Arrays.fill(c, mc);
		return str.replaceAll(".{" + length + "}$", String.valueOf(c));
	}

	/**
	 * 외국인등록번호
	 * 
	 * @param str
	 * @return
	 */
	public static String frn(String str, char mc) {
		int length = 3;
		char[] c = new char[length];
		Arrays.fill(c, mc);
		return str.replaceAll(".{" + length + "}$", String.valueOf(c));
	}
	
	/**
	 * 여권번호
	 * @param str
	 * @return
	 */
	public static String pn(String str, char mc) {
		int length = 3;
		char[] c = new char[length];
		Arrays.fill(c, mc);
		return str.replaceAll(".{" + length + "}$", String.valueOf(c));
	}

	/**
	 * 운전면허번호
	 * @param str
	 * @return
	 */
	public static String dln(String str, char mc) {
		if(str.lastIndexOf("-") > -1) {
			String mc_ = String.valueOf(mc);
			return str.replaceAll(".{1}-.{2}$", mc_ + "-" + mc_ + mc_);
		} else {
			int length = 3;
			char[] c = new char[length];
			Arrays.fill(c, mc);
			return str.replaceAll(".{" + length + "}$", String.valueOf(c));
		}
	}
	
	/**
	 * 전화번호, 휴대폰번호
	 * @param str
	 * @return
	 */
	public static String phone(String str, char mc) {
		String rStr = str;

		String firstNum = "2,3";
		if(str.startsWith("02")) {
			firstNum = "2";
		}
		Matcher matcher = Pattern.compile("^(\\d{" + firstNum + "})-?(\\d{3,4})-?(\\d{4})$").matcher(str);

		if (matcher.matches()) {
			rStr = "";

			boolean isHyphen = false;
			if (str.indexOf("-") > -1) {
				isHyphen = true;
			}

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 2) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}

				if (isHyphen && i < matcher.groupCount()) {
					rStr = rStr + "-";
				}
			}
		}

		return rStr;
	}

	/**
	 * 이메일주소
	 * 
	 * @param str
	 * @param mc
	 * @return
	 */
	public static String email(String str, char mc) {
		String rStr = str;

		Matcher matcher = Pattern.compile("^(.*)(.{3})([@]{1})(.*)$").matcher(str);

		if (matcher.matches()) {
			rStr = "";

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 2) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}
			}

		}

		return rStr;
	}

	/**
	 * 카드번호
	 * 
	 * @param str
	 * @param mc
	 * @return
	 */
	public static String card(String str, char mc) {
		String rStr = str;

		Matcher matcher = Pattern.compile("^(\\d{4})-?(..)(\\d{2})-?(\\d{3})(.)-?(\\d{4})$").matcher(str);

		if (matcher.matches()) {
			rStr = "";

			boolean isHyphen = false;
			if (str.indexOf("-") > -1) {
				isHyphen = true;
			}

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 3) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else if (i == 4) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}

				if (isHyphen && i < matcher.groupCount()) {
					if(i != 2 && i != 4) {
						rStr = rStr + "-";
					}
				}
			}
		}

		return rStr;
	}
	
	/**
	 * 계좌번호
	 * 
	 * @param str
	 * @return
	 */
	public static String an(String str, char mc) {
		int length = 4;
		char[] c = new char[length];
		Arrays.fill(c, mc);
		return str.replaceAll(".{" + length + "}$", String.valueOf(c));
	}
	
	public static String address(String str, char mc) {
		String rStr = str;

		Matcher matcher = Pattern.compile("(.*[시군구])(\\s?)(.*)([읍면동])(\\s?)(.*?)(\\s?)(.*?)").matcher(str);
		if(!matcher.matches()) {
			matcher = Pattern.compile("(.*[시군구])(\\s?)(.*)(로)(\\s?)(.*?)(\\s?)(.*?)").matcher(str);
		}
		
		if (matcher.matches()) {
			rStr = "";

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 3 || i == 6 || i == 8) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);
					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}
			}
		}

		return rStr;
	}
	
	/**
	 * IP주소
	 * 
	 * @param str
	 * @param mc
	 * @return
	 */
	public static String ip(String str, char mc) {
		String rStr = str;

		Matcher matcher = Pattern.compile("^([0-9]{1,3})\\.?([0-9]{1,3})\\.?([0-9]{1,3})\\.?([0-9]{1,3})$").matcher(str);

		if (matcher.matches()) {
			rStr = "";

			boolean isDot = false;
			if (str.indexOf(".") > -1) {
				isDot = true;
			}

			for (int i = 1; i <= matcher.groupCount(); i++) {
				String rtStr = matcher.group(i);
				if (i == 3) {
					char[] c = new char[rtStr.length()];
					Arrays.fill(c, mc);

					rStr = rStr + String.valueOf(c);
				} else {
					rStr = rStr + rtStr;
				}

				if (isDot && i < matcher.groupCount()) {
					rStr = rStr + ".";
				}
			}
		}

		return rStr;
	}

}