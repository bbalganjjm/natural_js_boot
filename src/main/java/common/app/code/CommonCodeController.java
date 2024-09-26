package common.app.code;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.app.code.service.impl.CommonCodeServiceImpl;;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
@RequestMapping("code")
public class CommonCodeController {

	@Resource(name = "commonCodeService")
	CommonCodeServiceImpl commonCodeService;

	@RequestMapping("getCommonCodeList.json")
	public List<Map<String, Object>> getCommonCodeList(@RequestBody Map<String, Object> vo) {
		return commonCodeService.getCommonCodeList(vo);
	}

	@RequestMapping(value = { "getCommonCodeCompanyList.json", "getCommonCodeCompanyList.xlsx" })
	public List<Map<String, Object>> getCommonCodeCompanyList(@RequestBody(required = false) Map<String, Object> vo) {
		return commonCodeService.getCommonCodeCompanyList(vo);
	}

}