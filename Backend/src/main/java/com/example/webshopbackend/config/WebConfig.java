/**
 * WebConfig Class
 *
 * This class configures the web application by enabling Cross-Origin Resource Sharing (CORS) and
 * specifying resource handling for image files.
 */
package com.example.webshopbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure Cross-Origin Resource Sharing (CORS) for the web application.
     *
     * This method allows requests from any origin and permits all HTTP methods.
     *
     * @param registry The CORS registry to configure.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    /**
     * Configure resource handling for image files.
     *
     * This method specifies the location of image resources and maps them to a specific URL path.
     * In this case, it maps the '/Frontend/images/upload-images/' URL path to the 'classpath:/static/img/' location.
     *
     * @param registry The resource handler registry to configure.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Frontend/images/upload-images/**")
                .addResourceLocations("classpath:/static/img/");
    }
}
