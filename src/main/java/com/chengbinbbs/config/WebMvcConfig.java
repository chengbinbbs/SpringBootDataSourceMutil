package com.chengbinbbs.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**@EnableWebMvc 无需使用该注解，否则会覆盖掉SpringBoot的默认配置值
 * @author zhangcb
 * @created 2017-07-10 14:12.
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter{

    /**
     * 自定义资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
        super.addResourceHandlers(registry);
    }

    /**
     * 页面跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }

//    @Bean
//    public LoginInterceptor loginInterceptor() {
//        return new LoginInterceptor();
//    }

    /**
     * 添加拦截器
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //对所有/order/请求都拦截，但是排除了/toLogin和/login请求的拦截
//        registry.addInterceptor(loginInterceptor()).addPathPatterns("/order/**").excludePathPatterns("toLogin","login");
//    }

}
