package common.naturaljs.app.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.naturaljs.app.sample.mappers.SampleDeptMapper;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Service("sampleDeptService")
public class SampleDeptServiceImpl {

	@Autowired
	private SampleDeptMapper sampleDeptMapper;

	public List<Map<String, Object>> getSampleDeptList(Map<String, Object> vo) {
		return sampleDeptMapper.getSampleDeptList(vo);
	}

}