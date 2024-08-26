/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.formatter.BaiDoXeFormatter;
import com.qlbdx.formatter.ChiTietKhuDoFormatter;
import com.qlbdx.formatter.ChoDoFormatter;
import com.qlbdx.formatter.DateFormatter;
import com.qlbdx.formatter.GiaFormatter;
import com.qlbdx.formatter.KhuDoXeFormatter;
import com.qlbdx.formatter.PhuongTienFormatter;
import com.qlbdx.formatter.StateFormatter;
import com.qlbdx.formatter.ThongTinDangKyFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebMvc
//@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.qlbdx.controllers",
    "com.qlbdx.repository",
    "com.qlbdx.service",
    "com.qlbdx.components"

})
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("RestTemplate Bean is created");
        return new RestTemplate();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");

        return m;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new BaiDoXeFormatter());
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new ChiTietKhuDoFormatter());
        registry.addFormatter(new StateFormatter());
        registry.addFormatter(new PhuongTienFormatter());
        registry.addFormatter(new GiaFormatter());
        registry.addFormatter(new ChoDoFormatter());
        registry.addFormatter(new KhuDoXeFormatter());
        registry.addFormatter(new ThongTinDangKyFormatter());

    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setMaxUploadSize(10000000); // Kích thước tối đa của file upload (10MB)
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/resources/plugins/");
        registry.addResourceHandler("/dist/**").addResourceLocations("/resources/dist/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        r.setViewClass(JstlView.class);
//        r.setPrefix("/WEB-INF/pages/");
//        r.setSuffix(".jsp");
//        
//        return r;
//    }
}
