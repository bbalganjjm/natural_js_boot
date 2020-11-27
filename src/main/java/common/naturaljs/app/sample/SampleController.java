package common.naturaljs.app.sample;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.naturaljs.app.sample.service.impl.SampleServiceImpl;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
@RequestMapping("sample")
public class SampleController {

	@Resource(name = "sampleService")
	SampleServiceImpl sampleService;

	@RequestMapping("getSampleList")
	public List<Map<String, Object>> getSampleList(@RequestBody(required = false) Map<String, Object> vo) {
		return sampleService.getSampleList(vo);
	}

	@RequestMapping("getSampleBigList")
	public List<Map<String, Object>> getSampleBigList(@RequestBody(required = false) Map<String, Object> vo) {
		return sampleService.getSampleBigList(vo);
	}

	@RequestMapping("getSample")
	public List<Map<String, Object>> getSample(@RequestBody Map<String, Object> vo) {
		return sampleService.getSample(vo);
	}

	@RequestMapping("saveSample")
	public Map<String, Object> saveSample(@RequestBody List<Map<String, Object>> voList) {
		return sampleService.saveSample(voList);
	}

	@RequestMapping("insertSample")
	public int insertSample(@RequestBody Map<String, Object> vo) {
		return sampleService.insertSample(vo);
	}

	@RequestMapping("updateSample")
	public int updateSample(@RequestBody Map<String, Object> vo) {
		return sampleService.updateSample(vo);
	}

	@RequestMapping("deleteSample")
	public int deleteSample(@RequestBody Map<String, Object> vo) {
		return sampleService.deleteSample(vo);
	}

}