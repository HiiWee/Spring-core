package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring");
        return "hello"; // viewResolver로 resources/templates/hello.html의 파일을 찾아서 렌더링하게 함(화면을 실행시킴)
    }
}
