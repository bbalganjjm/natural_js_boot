package common.app.file.service;

import java.util.List;
import java.util.Map;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2019.03.21
 */
public interface FileService {
	
	public Map<String, Object> saveFile(List<Map<String, Object>> saveFileList);
	
	public List<Map<String, Object>> getFileList(Map<String, Object> vo);
	
	public Map<String, Object> getFile(Map<String, Object> vo);
	
	public int deleteFile(Map<String, Object> vo);
	
}
