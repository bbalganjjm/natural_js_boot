package common.app.shell;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
public class ShellController {

    @RequestMapping("index.view")
    public String index() {
        return "comm/shell/index";
    }

    @RequestMapping("header.view")
    public String header() {
        return "comm/shell/header";
    }

    @RequestMapping("contents.view")
    public String contents() {
        return "comm/shell/contents";
    }

    @RequestMapping("lefter.view")
    public String lefter() {
        return "comm/shell/lefter";
    }

    @RequestMapping("footer.view")
    public String footer() {
        return "comm/shell/footer";
    }
}