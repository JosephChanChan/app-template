package com.joseph.template.api.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Joseph
 * @since 2020-06-18 20:53
 */
@Controller
public class PortalController {

    @GetMapping("/")
    public String portal() {
        return "index";
    }

    @GetMapping("/backendPortal")
    public String backendPortal() {
        return "backendlogin";
    }

    @GetMapping("/developerPortal")
    public String developerPortal() {
        return "devlogin";
    }


}
