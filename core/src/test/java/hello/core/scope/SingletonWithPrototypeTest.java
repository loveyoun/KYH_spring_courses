package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

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
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);   // @ComponentScan 대상이 되는 효과. 자동 빈 등록

        ObjectProvider<PrototypeBean> prototypeBeanProvider = ac.getBeanProvider(PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        clientBean1.setPrototypeBeanProvider(prototypeBeanProvider);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);


        assertThat(clientBean1).isSameAs(clientBean2);

        int count1 = clientBean1.logic();   // getObject() : 최초 생성
        assertThat(count1).isEqualTo(1);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }


    static class ClientBean {
//        private final PrototypeBean prototypeBean;   // 생성 시점에 주입 때만 생성
//        private final Provider<PrototypeBean> prototypeBeanProvider;   // ObjectProvider : 스프링이 자동 주입

//      @Autowired ApplicationContext applicationContext;   // 무식한 방법

//        @Autowired
//        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {   //(PrototypeBean prototypeBean) {
////            this.prototypeBean = prototypeBean;
//            this.prototypeBeanProvider = prototypeBeanProvider;   // <T> 반드시
//        }

        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // @Autowired 없어도 자동 등록해줘??? 그건 아니고, clientBean1 에서 등록했으니까, ClientBean 싱글톤이잖아. ObjectProvider 도 싱글톤.
        public void setPrototypeBeanProvider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
            System.out.println("ClientBean.setPrototypeBeanProvider = " + prototypeBeanProvider);
            System.out.println("ClientBean.setPrototypeBeanProvider = " + this.prototypeBeanProvider);
        }


        public int logic() {
//            System.out.println("prototypeBean 생성");   // getObject() 후 init()

//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);   // 무식한 방법. 호출(요청) 시마다 생성
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();   // ObjectProvider<>
//            PrototypeBean prototypeBean = prototypeBeanProvider.get();   // Provider<>

            System.out.println("logic() = " + prototypeBean);

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
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
