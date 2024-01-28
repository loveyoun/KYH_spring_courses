package hello.embed;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class EmbedTomcatSpringMain {

    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatSpringMain.main");

        // 톰캣 생성
        Tomcat tomcat = new Tomcat();

        // 톰캣 설정
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        /**
         * 어플리케이션 초기화
         */
        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext =
                new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 스프링 MVC 에 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 서블릿 컨테이너 생성, 초기화
        Context servletContext = tomcat.addContext("", "/");

        //=== 코드 추가 시작 ===
        File docBaseFile = new File(servletContext.getDocBase());
        if (!docBaseFile.isAbsolute()) {
            docBaseFile = new File(((org.apache.catalina.Host)
                    servletContext.getParent()).getAppBaseFile(), docBaseFile.getPath());
        }
        docBaseFile.mkdirs();
        //=== 코드 추가 종료 ===

        // 디스패처 서블릿 등록
        tomcat.addServlet("", "dispatcher", dispatcher);
        // -> servletContext.addServlet()

        servletContext.addServletMappingDecoded("/", "dispatcher");
        // -> servlet.addMapping()

        tomcat.start();
    }


}