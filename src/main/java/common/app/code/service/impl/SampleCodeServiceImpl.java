package common.app.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.app.code.mappers.SampleCodeMapper;;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Service("sampleCodeService")
public class SampleCodeServiceImpl {

	@Autowired
	private SampleCodeMapper sampleCodeMapper;

	public List<Map<String, Object>> getSampleCodeList(Map<String, Object> vo) {
		return sampleCodeMapper.getSampleCodeList(vo);
	}

	public List<Map<String, Object>> getSampleCodeCompanyList(Map<String, Object> vo) {
		return sampleCodeMapper.getSampleCodeCompanyList(vo);
	}

}