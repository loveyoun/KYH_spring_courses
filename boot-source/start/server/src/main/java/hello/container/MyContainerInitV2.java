package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)  // 이 구현체들의 class 정보가 Set<Class<?>> 에 딸려온다.
public class MyContainerInitV2 implements ServletContainerInitializer {
    // 등록해 주어야 서블릿 초기화 코드 실행.

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 container = " + ctx);

        for (Class<?> appInitClass : c) { // 클래스 메타 정보가 넘어온다.
            try {
                AppInit appInit =
                        (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                // == new AppInitV1Servlet()

                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }


}