package hello.core.web;

/**
 * FAILED
 **/

import hello.core.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RequestScopeTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(LogService.class, MyLogger.class);

//        ObjectProvider<MyLogger> myLoggerProvider = ac.getBeanProvider(MyLogger.class);
//        MyLogger myLogger = myLoggerProvider.getObject();   // 최초 생성
//        /*IllegalStateException: No Scope registered for scope name 'request'*/
//
//        myLogger.setRequestURL("non-HTTP");
//        myLogger.log("non-HTTP test");

        LogService logService = ac.getBean(LogService.class);
        logService.logic("testId");
    }


    static class LogService {

        private final ObjectProvider<MyLogger> myLoggerProvider;   // in the Spring Container as Spring Bean


        public LogService(ObjectProvider<MyLogger> myLoggerProvider) {
            this.myLoggerProvider = myLoggerProvider;
        }

        public void logic(String id) {
            /*IllegalStateException: No Scope registered for scope name 'request'*/
            // 가정 : HTTP request 가 들어와야지 scope("request") 발동?
            MyLogger myLogger = myLoggerProvider.getObject();

            myLogger.log("service id = " + id);
        }

    }


}
