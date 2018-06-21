package ocean.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheOpen {
//  RedisCacheNamespace nameSpace();
    String prefix();

    String end() default "";
}