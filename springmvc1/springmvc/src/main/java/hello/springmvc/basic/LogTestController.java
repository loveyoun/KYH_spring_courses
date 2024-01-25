package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j  // lombok 제공
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass()); // == @Slf4j


    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);


        // 더하기 연산 미리 되어서 메모리, CPU 등 쓸모없는 resource 사용
        log.trace(" trace my log=" + name);

        // parameter 넘기기
        // == if(log.isTraceEnabled()) {}
        log.trace("trace log={}", name);  // 로컬 서버
        log.debug("debug log={}", name);  // 개발 서버에서 필요한 정보
        log.info(" info log={}", name);   // 운영 서버
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }


}
