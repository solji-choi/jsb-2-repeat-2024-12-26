package com.ll.sbb2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/sbb")
    public void index() {
        System.out.println("index");
    }
}
