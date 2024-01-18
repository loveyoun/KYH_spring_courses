package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet Mapping
@WebServlet(name = "helloServlet", urlPatterns = "/hello")  // /hello 로 현재 서블릿이 호출되면, service() 가 자동 호출된다.
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");   // Http Header Content-Type
        response.setCharacterEncoding("utf-8");  // Http Header Content-Type

        response.getWriter().write("hello " + username);  // HTTP body 에 들어가는 data

    }

}
