package common.app.code;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.app.code.service.impl.SampleCodeServiceImpl;;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
@RequestMapping("sample/code")
public class SampleCodeController {

	@Resource(name = "sampleCodeService")
	SampleCodeServiceImpl sampleCodeService;

	@RequestMapping("getSampleCodeList.json")
	public List<Map<String, Object>> getSampleCodeList(@RequestBody Map<String, Object> vo) {
		return sampleCodeService.getSampleCodeList(vo);
	}

	@RequestMapping("getSampleCodeCompanyList.json")
	public List<Map<String, Object>> getSampleCodeCompanyList(@RequestBody(required = false) Map<String, Object> vo) {
		return sampleCodeService.getSampleCodeCompanyList(vo);
	}

}