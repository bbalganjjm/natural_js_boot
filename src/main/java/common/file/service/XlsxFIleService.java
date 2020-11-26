package common.file.service;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2019.06.25
 */

public interface XlsxFIleService {
	
	public File postProcessing(MultipartHttpServletRequest mRequest, Map<String, Object> paramMap);
	
}
