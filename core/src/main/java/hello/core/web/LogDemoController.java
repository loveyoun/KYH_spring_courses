package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;   // Proxy 이용

//    private ApplicationContext ac;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
//        System.out.println("웹 전용 스프링 컨테이너 확인 = " + ac);
        String requestURL = request.getRequestURL().toString();

//        MyLogger myLogger = myLoggerProvider.getObject();   // 최초 생성
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        Thread.sleep(1000);   // request scope : 각 요청에 각각 빈 생성

        logDemoService.logic("testId");

        return "OK";
    }

}
