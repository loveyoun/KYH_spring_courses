package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerMapping 구현체 중, BeanNameUrlHandlerMapping 의 부모 등으로 이거 찾음
 * -> HandlerAdapter 의 구현체 중, SimpleControllerHandlerAdapter.handle() 실행
 * -> ((Controller) handler).handleRequest(request, response) 호출
 * -> ViewResolver 구현체 중, InternalResourceViewResolver.buildView()
 * -> InternalResourceView
 */

@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }

}
