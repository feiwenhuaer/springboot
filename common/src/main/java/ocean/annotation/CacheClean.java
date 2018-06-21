package ocean.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheClean {
    //  RedisCacheNamespace nameSpace();
    String prefix();

    String end();
}
