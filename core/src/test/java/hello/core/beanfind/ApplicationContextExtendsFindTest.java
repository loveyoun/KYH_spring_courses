package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtendConfig.class);   //DiscountPolicyConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 이름 지정")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 타입으로 조회")
    void findBeanBySubType() {
        DiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicy.class);

        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet())
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회 _ Object Type")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        /* new AnnotationConfigApplicationContext(DiscountPolicyConfig.class);
         *
         * IllegalStateException:
         * No Scope registered for scope name 'request'
         * if @ComponentScan in DiscountPolicyConfig.class
         *
         * @ComponentScan 중복이라서가 아님
         * getBeansOfType 했을 때는 요청이 들어오지 않아서 request Scope 객체가 생성되지 않아서.
         *
         * MyLogger.class 에서 @Scope("request") 를 주석처리하면 된다.
         * 하지만 DiscountPolicyConfig 는 설정 정보로, @ComponentScan 이 필요하진 않는다.
         * 그냥 맘편히 임의의 Config.class 를 생성해준다.
         * */

        for (String key : beansOfType.keySet())
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
    }


    //@Configuration
    /* IllegalStateException: Failed to load ApplicationContext
     * Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException:
     * Invalid bean definition with name 'rateDiscountPolicy' defined in class path resource [hello/core/beanfind/ApplicationContextExtendsFindTest$ExtendConfig.class]:
     * Cannot register bean definition defined in class path resource [hello/core/beanfind/ApplicationContextExtendsFindTest$ExtendConfig.class]]
     * for bean 'rateDiscountPolicy':
     * There is already defined in class path resource [hello/core/AppConfig.class]] bound.
     * */
    static class ExtendConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }

}
