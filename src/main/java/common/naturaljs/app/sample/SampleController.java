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

    @RequestMapping("getSampleList.json")
    public List<Map<String, Object>> getSampleList(@RequestBody(required = false) Map<String, Object> vo) {
        return sampleService.getSampleList(vo);
    }

    @RequestMapping(value = { "getSampleBigList.json", "getSampleBigList.xlsx" })
    public List<Map<String, Object>> getSampleBigList(@RequestBody(required = false) Map<String, Object> vo) {
        return sampleService.getSampleBigList(vo);
    }

    @RequestMapping("getSample.json")
    public List<Map<String, Object>> getSample(@RequestBody Map<String, Object> vo) {
        return sampleService.getSample(vo);
    }

    @RequestMapping("saveSample.json")
    public Map<String, Object> saveSample(@RequestBody List<Map<String, Object>> voList) {
        return sampleService.saveSample(voList);
    }

    @RequestMapping("insertSample.json")
    public int insertSample(@RequestBody Map<String, Object> vo) {
        return sampleService.insertSample(vo);
    }

    @RequestMapping("updateSample.json")
    public int updateSample(@RequestBody Map<String, Object> vo) {
        return sampleService.updateSample(vo);
    }

    @RequestMapping("deleteSample.json")
    public int deleteSample(@RequestBody Map<String, Object> vo) {
        return sampleService.deleteSample(vo);
    }

}