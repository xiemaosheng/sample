package com.nd.config;

import com.nd.gaea.rest.config.WafWebMvcConfigurerAdapter;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/8/17 0017
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.nd"})
@ImportResource(value = {"classpath:spring/applicationContext.mongodb.xml"})
public class WebMvcConfig extends WafWebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //.setCachePeriod()设置资源缓存周期
    }

    @Bean
    public static PropertyPlaceholderConfigurer getPropertyConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        Resource transResource = new ClassPathResource("config/jdbc.properties");
        configurer.setLocations(new Resource[]{transResource});
        return configurer;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


}
