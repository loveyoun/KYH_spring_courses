package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    /**
     * Assumption : For every @Test,
     * all codes re-run or
     * Spring re-run
     **/
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("모든 빈 이름으로 조회해서 출력하기")
    void findAllBean() {  // BeanName -> getBean()
        System.out.println("<ApplicationContextInfoTest.findAllBean>");

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            // key(이름), value(객체)
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }

    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {  // BeanName -> BeanDef -> BeanRole -> getBean()
        System.out.println("<ApplicationContextInfoTest.findApplicationBean>");

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // GenericApplicationContext.class method

            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);

                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }

        }

    }


}
