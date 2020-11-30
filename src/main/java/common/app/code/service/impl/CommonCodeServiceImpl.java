package common.app.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.app.code.mappers.CommonCodeMapper;;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Service("sampleCodeService")
public class CommonCodeServiceImpl {

	@Autowired
	private CommonCodeMapper sampleCodeMapper;

	public List<Map<String, Object>> getCommonCodeList(Map<String, Object> vo) {
		return sampleCodeMapper.getCommonCodeList(vo);
	}

	public List<Map<String, Object>> getCommonCodeCompanyList(Map<String, Object> vo) {
		return sampleCodeMapper.getCommonCodeCompanyList(vo);
	}

}