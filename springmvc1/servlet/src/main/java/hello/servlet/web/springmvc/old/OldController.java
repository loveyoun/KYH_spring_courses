package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerMapping 구현체 중, BeanNameUrlHandlerMapping 의 부모 등으로 이거 찾음
 * -> HandlerAdapter 의 구현체 중, SimpleControllerHandlerAdapter.handle() : 이거 실행
 * return ((Controller) handler).handleRequest(request, response);
 * -> ViewResolver 구현체 중, InternalResourceViewResolver.buildView()
 * -> InternalResourceView
 */

// @Controller 말고, HandlerMapping 을 따로 해주기
// SpringBean 이름을 URL 패턴으로
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }

}
