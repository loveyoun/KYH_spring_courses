package hello.container;

import jakarta.servlet.ServletContext;

public interface AppInit {

    void onStartup(ServletContext servletContext);
    // 서블릿 초기화 시 필요한 필터나 서블릿 등록: 애플리케이션 초기화

}
