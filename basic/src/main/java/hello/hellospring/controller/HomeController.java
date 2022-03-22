package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 도메인의 제일 처음 주소
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
