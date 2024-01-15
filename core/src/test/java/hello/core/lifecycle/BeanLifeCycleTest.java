package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();   /* ApplicationContext 에는 없는 메서드 */
        // AnnotationConfigApplicationContext extends GenericApplicationContext
        // extends `AbstractApplicationContext` implements ConfigurableApplicationContext extends ApplicationContext
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean   //(initMethod = "init", destroyMethod = "close")   // ref : AutoCloseable
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();

            networkClient.setUrl("http://hello-world");

            return networkClient;
        }
    }

}
