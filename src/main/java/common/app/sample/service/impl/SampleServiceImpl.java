package common.app.sample.service.impl;

import common.app.sample.mappers.SampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Service("sampleService")
public class SampleServiceImpl {

	@Autowired
	private SampleMapper sampleMapper;

	public List<Map<String, Object>> getSampleList(Map<String, Object> vo) {
		return sampleMapper.getSampleList(vo);
	}

	public List<Map<String, Object>> getSampleBigList(Map<String, Object> vo) {
		return sampleMapper.getSampleBigList(vo);
	}

	public List<Map<String, Object>> getSample(Map<String, Object> vo) {
		return sampleMapper.getSample(vo);
	}

	public Map<String, Object> saveSampleList(List<Map<String, Object>> voList) {
		Iterator<Map<String, Object>> iter = voList.iterator();

		Map<String, Object> vo;
		int insert = 0;
		int update = 0;
		int delete = 0;
		while (iter.hasNext()) {
			vo = (Map<String, Object>) iter.next();
			if ("insert".equals((String) vo.get("rowStatus"))) {
				insert += this.insertSample(vo);
			} else if ("update".equals((String) vo.get("rowStatus"))) {
				update += this.updateSample(vo);
			} else if ("delete".equals((String) vo.get("rowStatus"))) {
				delete += this.deleteSample(vo);
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("insert", insert);
		resultMap.put("update", update);
		resultMap.put("delete", delete);

		return resultMap;
	}

	public int insertSample(Map<String, Object> vo) {
		return sampleMapper.insertSample(vo);
	}

	public int updateSample(Map<String, Object> vo) {
		return sampleMapper.updateSample(vo);
	}

	public int deleteSample(Map<String, Object> vo) {
		return sampleMapper.deleteSample(vo);
	}

}