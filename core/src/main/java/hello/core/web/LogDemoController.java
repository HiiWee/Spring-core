package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // private final MyLogger myLogger;
    // MyLogger를 주입받는게 아니라 MyLogger를 찾을 수 있는(DI) 객체가 주입된다.
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view화면이 없으므로 문자를 그대로 응답으로 보낼 수 있다.
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        // MyLogger가 아닌 가짜 프록시 객체인 MyLogger$CGLIB객체가 빈으로 등록된다.
        System.out.println("myLogger = " + myLogger.getClass());
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setReqeustURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";
    }

}
