package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private final String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }


    // V2
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  // JSP 자동 렌더링
    }

    // V3, V4
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Model to HTTP request
        /*
         * HTTP response has no method .setAttribute()
         * Only .setStatus()
         */
        modelToRequestAttribute(model, request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  // JSP 가 request.getAttribute() 해서 쓴다.
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // lambda : (key, value) 로 변수 꺼내서, 함수 적용
        model.forEach(request::setAttribute);  // key = "members", value = List<Member>
//        model.forEach((key, value) -> request.setAttribute(key, value));
    }

}
