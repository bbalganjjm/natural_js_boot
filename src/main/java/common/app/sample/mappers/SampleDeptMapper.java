package common.app.sample.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Mapper
public interface SampleDeptMapper {

	public List<Map<String, Object>> getSampleDeptList(Map<String, Object> vo);

}
