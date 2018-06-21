package ocean.aspect;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import ocean.annotation.AnnotationResolverUtil;
import ocean.annotation.CacheOpen;
import ocean.JedisApi;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * AOP实现Redis缓存处理
 *
 * @author xieyi
 */
@Slf4j
@Component
@Aspect
public class RedisAspect {
    @Autowired
    @Qualifier("redisCache")
    private JedisApi jedisApi;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut(value = "@annotation(ocean.annotation.CacheOpen)")
    public void pointcutMethod() {

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        Object[] args = joinPoint.getArgs();
        Arrays.asList(args).forEach(arg->{
            if(Objects.isNull(arg)){
                log.warn("参数为空：{}",arg.toString());
            }
        });
        //获取目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //得到注解
        CacheOpen redisCache = method.getAnnotation(CacheOpen.class);
        Object firstKey = AnnotationResolverUtil.newInstance().resolver(joinPoint, redisCache.prefix());
        Object secondKey = AnnotationResolverUtil.newInstance().resolver(joinPoint, redisCache.end());
        if(Objects.isNull(firstKey)){
            throw new RuntimeException("error happend while setting parameter in class:"+target+", method:"+method.getName()+", null not allow here!");
        }
        if(Objects.isNull(secondKey) || Objects.equals("",secondKey)) {
            return selectByOneKey(firstKey.toString(), joinPoint);
        }else {
            return selectByFullKey(firstKey.toString(), secondKey.toString(), joinPoint);
        }

    }

    private Object selectByOneKey(String key, ProceedingJoinPoint joinPoint) {
        Object obj = jedisApi.getObject(key);
        if (Objects.nonNull(obj)) {
            return obj;
        }
        obj = exePoint(obj,joinPoint);
        if(!Objects.isNull(obj)) {
            jedisApi.saveObject(key.toString(), obj);
        }
        return obj;
    }

    private Object selectByFullKey(String key1, String key2, ProceedingJoinPoint joinPoint) {
        Object obj = jedisApi.getHashes(key1,key2);
        if (Objects.nonNull(obj)) {
            return obj;
        }
        obj = exePoint(obj,joinPoint);
        if(!Objects.isNull(obj)) {
            jedisApi.setHashes(key1, key2, obj);
        }
        return obj;
    }

    private Object exePoint(Object obj, ProceedingJoinPoint joinPoint){
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            log.debug(Throwables.getStackTraceAsString(e));
        }
        return obj;
    }
}