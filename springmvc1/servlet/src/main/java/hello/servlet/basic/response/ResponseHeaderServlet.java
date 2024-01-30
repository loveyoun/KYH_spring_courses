package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");  // 과거 캐시까지 무효화
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
//        content(response);
//        cookie(response);
        redirect(response);

        // [Message Body]
        response.getWriter().println("ok");  // or getOutputStream()
    }

    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2

        response.setContentType("text/plain");
//        ==  response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2);  // 생략시 자동 생성
    }

    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);   // 600초

        response.addCookie(cookie);
//        == response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND);  // 마지막 상태 코드가 덮어버림
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

}
