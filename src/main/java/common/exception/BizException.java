package common.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 4739641580572707055L;

	public static final int DEFAULT_ERROR_CODE = -3;
	
	private int code;
	private String message;
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 메시지를 직접 정의
	 * 
	 * @param code
	 * @param message
	 */
	public BizException(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	/**
	 * 메시지가 메시지 키인 메시지 반환
	 * 
	 * @param code
	 * @param message
	 * @param args
	 */
	public BizException(int code, String message, String[] args) {
		WebApplicationContext webAppContext = RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
		MessageSource messageSource = (MessageSource)webAppContext.getBean("messageSource");
		
		this.setCode(code);
		this.setMessage(messageSource.getMessage(message, args, Locale.getDefault()) );
	}
	
	/**
	 * 코드가 메시지 키인 메시지 반환
	 * 
	 * @param code
	 */
	public BizException(int code) {
		WebApplicationContext webAppContext = RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
		MessageSource messageSource = (MessageSource)webAppContext.getBean("messageSource");
		
		this.setCode(code);
		this.setMessage(messageSource.getMessage(String.valueOf(code), null, Locale.getDefault()));
	}
	
	/**
	 * 코드가 메시지 키인 메시지 반환
	 * 
	 * @param code
	 * @param args
	 */
	public BizException(int code, String[] args) {
		WebApplicationContext webAppContext = RequestContextUtils.findWebApplicationContext(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
		MessageSource messageSource = (MessageSource)webAppContext.getBean("messageSource");
		
		this.setCode(code);
		this.setMessage(messageSource.getMessage(String.valueOf(code), args, Locale.getDefault()) );
	}
}
