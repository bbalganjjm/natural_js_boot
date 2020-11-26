package common.naturaljs.app.sample.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Mapper
public interface SampleMapper {

	public List<Map<String, Object>> getSampleList(Map<String, Object> vo);

	public List<Map<String, Object>> getSampleBigList(Map<String, Object> vo);

	public List<Map<String, Object>> getSample(Map<String, Object> vo);

	public int insertSample(Map<String, Object> vo);

	public int updateSample(Map<String, Object> vo);

	public int deleteSample(Map<String, Object> vo);

}
