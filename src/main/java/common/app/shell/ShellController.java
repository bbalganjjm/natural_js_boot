package common.app.shell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Goldman Kim( bbalganjjm@gmail.com )
 * @since 2018.12.05
 */

@Controller
public class ShellController {

    @RequestMapping("index.view")
    public String index() {
        return "common/shell/index";
    }

    @RequestMapping("header.view")
    public String header() {
        return "common/shell/header";
    }

    @RequestMapping("contents.view")
    public String contents() {
        return "common/shell/contents";
    }

    @RequestMapping("lefter.view")
    public String lefter() {
        return "common/shell/lefter";
    }

    @RequestMapping("footer.view")
    public String footer() {
        return "common/shell/footer";
    }
}
