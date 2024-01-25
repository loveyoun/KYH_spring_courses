package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HandlerMapping 구현체 중, BeanNameUrlHandlerMapping 의 부모 등으로 이거 찾음
 * -> HandlerAdapter 의 구현체 중, HttpRequestHandlerAdapter.handle() : 이거 실행
 * ((HttpRequestHandler) handler).handleRequest(request, response);
 */

// @Controller 말고, HandlerMapping 을 따로 해주기
// SpringBean 이름을 URL 패턴으로
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }


}
