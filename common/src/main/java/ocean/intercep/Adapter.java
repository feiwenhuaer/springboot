package ocean.intercep;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义拦截器
 * @author xieyi
 */
@Configuration
public class Adapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 主要方法说明：
         * addPathPatterns 用于添加拦截规则,可添加多个
         * excludePathPatterns 用户排除拦截
         * /*只能拦截单层级路径
         * /**可以拦截多层级
         */
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);

    }
}