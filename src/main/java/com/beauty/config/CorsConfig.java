package com.beauty.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录配置
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private CustomerLoginInterceptor customerLoginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        registry.addInterceptor(customerLoginInterceptor).addPathPatterns("/**");

        super.addInterceptors(registry);
    }


    /**
     * （1）在启动类App.java类中继承：WebMvcConfigurerAdapter
     * （2）覆盖方法：configureContentNegotiation
     * favorPathExtension表示支持后缀匹配，
     * 属性ignoreAcceptHeader默认为false，表示accept-header匹配，defaultContentType开启默认匹配。
     * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
     * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);

    }

    @Bean
    public Converter<String, Date> addDateConvert() throws Exception {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = null;
                try {
                    String regex1 = "^\\d{4}-\\d{2}-\\d{2}$";
                    String regex2 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
                    String regex3 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
                    String regex4 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}$";
                    if (isMatch(source, regex1)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        return sdf.parse(source);
                    }
                    if (isMatch(source, regex2)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        return sdf.parse(source);
                    }
                    if (isMatch(source, regex3)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        return sdf.parse(source);
                    }
                    if (isMatch(source, regex4)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        return sdf.parse(source);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    private boolean isMatch(String source, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

}
