package TiaLU.Storage.Information;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)

public @interface Configurable {
    String description() default ""; // 可添加描述信息
}