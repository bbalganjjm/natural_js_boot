package common.app.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.app.code.mappers.CommonCodeMapper;;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Service("commonCodeService")
public class CommonCodeServiceImpl {

	private final CommonCodeMapper commonCodeMapper;

	public CommonCodeServiceImpl(CommonCodeMapper commonCodeMapper) {
		this.commonCodeMapper = commonCodeMapper;
	}

	public List<Map<String, Object>> getCommonCodeList(Map<String, Object> vo) {
		return commonCodeMapper.getCommonCodeList(vo);
	}

	public List<Map<String, Object>> getCommonCodeCompanyList(Map<String, Object> vo) {
		return commonCodeMapper.getCommonCodeCompanyList(vo);
	}

}