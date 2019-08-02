package kansha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-01 16:44
 **/
@RestController
public class TestController {

    @RequestMapping("test1")
    public String testController(){


        return "2003";
    }
}
