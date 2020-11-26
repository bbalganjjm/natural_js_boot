package common.file.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Mapper
public interface FileMapper {

	public int insertFile(Map<String, Object> vo);

	public List<Map<String, Object>> getFileList(Map<String, Object> vo);

	public Map<String, Object> getFile(Map<String, Object> vo);

	public int deleteFile(Map<String, Object> vo);

}
