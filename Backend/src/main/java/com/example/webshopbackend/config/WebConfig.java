package com.example.webshopbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
<<<<<<< HEAD
     registry.addResourceHandler("/Frontend/images/upload-images/**")
=======
        registry.addResourceHandler("/Frontend/images/upload-images/**")
>>>>>>> 57c3633fc76e76b5a1975c7a5514d9bd78a2df49
                .addResourceLocations("classpath:/static/img/");
    }
}
