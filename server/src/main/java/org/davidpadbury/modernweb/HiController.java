package org.davidpadbury.modernweb;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HiController {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "Yep - hello";
    }

}
