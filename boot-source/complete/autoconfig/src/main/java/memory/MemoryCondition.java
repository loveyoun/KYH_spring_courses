package memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class MemoryCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // -Dmemory=on. 메모리 기능을 언제 활성화할지 지정.
        String memory = context.getEnvironment().getProperty("memory");
        log.info("memory = {}", memory);

        return "on".equals(memory);
    }


}
