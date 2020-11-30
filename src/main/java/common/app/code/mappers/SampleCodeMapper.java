package common.app.code.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Mapper
public interface SampleCodeMapper {

	public List<Map<String, Object>> getSampleCodeList(Map<String, Object> vo);

	public List<Map<String, Object>> getSampleCodeCompanyList(Map<String, Object> vo);

}
