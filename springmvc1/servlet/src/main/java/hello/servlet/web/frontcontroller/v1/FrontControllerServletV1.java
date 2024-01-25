package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * V1
 * FrontController 도입 : URL 배분
 * 아직 각 Controller : Service, Repository 역할 + Controller(View 로 보내고 Model 에 저장) 역할
 */

// /front-controller/v1/~~~ 요청오면 일단 이거 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // key = /front-controller/v1/members/new-form, value = 구현체
        String requestURI = request.getRequestURI();

        // 다형성
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        controller.process(request, response);
        /*
         * (중복 제거)
         * 각 Controller 가 직접 View 로 보내는 것이 아니라 : dispatcher.forward(request, response),
         * Controller 는 .jsp 만 알려주고, JSP 로 forward() 하는 클래스 도입
         * -> FrontControllerServletV2 로 개선
         */
    }


}
