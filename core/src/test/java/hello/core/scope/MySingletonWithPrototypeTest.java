package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class MySingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        // @ComponentScan 대상이 되는 효과. 자동 빈 등록

        ObjectProvider<PrototypeBean> prototypeBeanProvider = ac.getBeanProvider(PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        System.out.println("prototypeBean 생성");
        PrototypeBean prototypeBean1 = prototypeBeanProvider.getObject();
        clientBean1.setPrototypeBean(prototypeBean1);

        int count1 = clientBean1.logic();   // getObject() : 최초 생성
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        System.out.println("prototypeBean 생성");
        PrototypeBean prototypeBean2 = prototypeBeanProvider.getObject();
        clientBean2.setPrototypeBean(prototypeBean2);


        /*
        이렇게 같이 하면 오류남. 싱글톤 ClientBean 에 마지막 PrototypeBean 으로 교체돼서.
        Prototype Scope 특성은, 그 안에서 생성, 초기화 하고 클라이언트에 반환하고 끝.
        */
//        int count1 = clientBean1.logic();
//        assertThat(count1).isEqualTo(1);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }


    static class ClientBean {
        private PrototypeBean prototypeBean;   // 생성 시점에 주입 때만 생성

//        @Autowired ApplicationContext ac;   // 무식한 방법
//
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        private final Provider<PrototypeBean> prototypeBeanProvider;  // ObjectProvider : 스프링이 자동 주입
//
//        @Autowired
//        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
//            this.prototypeBeanProvider = prototypeBeanProvider;   // <T> 반드시
//        }

        public void setPrototypeBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
            System.out.println("ClientBean.setPrototypeBean = " + prototypeBean);
        }


        public int logic() {
//            System.out.println("prototypeBean 생성");   // getObject() 후 init()

//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);   // 무식한 방법. 호출(요청) 시마다 생성
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // ObjectProvider<>
//            PrototypeBean prototypeBean = prototypeBeanProvider.get();       // Provider<>

            System.out.println("logic() = " + this.prototypeBean);

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }


}
