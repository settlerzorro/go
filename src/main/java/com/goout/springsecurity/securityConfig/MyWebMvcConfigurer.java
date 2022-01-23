package com.goout.springsecurity.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 资源映射路径
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler：指的是对外暴露的访问路径
        //addResourceLocations：指的是内部文件放置的目录
        //通过设置spring.resources.static-locations自定义Spring boot加载前端静态资源路径
//        如果指定了拦截器，该属性有可能失效
//        需要在拦截器ResourceHandlerRegistry中通过addLocations()指定对应路径
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:/Users/ll/Downloads/upload/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
    }
}


