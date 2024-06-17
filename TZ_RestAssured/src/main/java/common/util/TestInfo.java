package common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * https://www.mindstick.com/articles/12141/annotations-in-java-target-and-retention
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestInfo {
    String id() default "unknown";
    String description() default "unknown";
}
