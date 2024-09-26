package common.app.sample;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.app.sample.service.impl.SampleDeptServiceImpl;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
@RequestMapping("sample")
public class SampleDeptController {

	@Resource(name = "sampleDeptService")
	SampleDeptServiceImpl sampleDeptService;

	@RequestMapping("getSampleDeptList.json")
	public List<Map<String, Object>> getSampleDeptList(@RequestBody(required = false) Map<String, Object> vo) {
		return sampleDeptService.getSampleDeptList(vo);
	}

}