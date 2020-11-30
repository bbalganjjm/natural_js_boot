package common.app.code;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.app.code.service.impl.CommonCodeServiceImpl;;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
@RequestMapping("code")
public class CommonCodeController {

	@Resource(name = "sampleCodeService")
	CommonCodeServiceImpl sampleCodeService;

	@RequestMapping("getCommonCodeList.json")
	public List<Map<String, Object>> getCommonCodeList(@RequestBody Map<String, Object> vo) {
		return sampleCodeService.getCommonCodeList(vo);
	}

	@RequestMapping("getCommonCodeCompanyList.json")
	public List<Map<String, Object>> getCommonCodeCompanyList(@RequestBody(required = false) Map<String, Object> vo) {
		return sampleCodeService.getCommonCodeCompanyList(vo);
	}

}