package br.com.alfabetiza.api.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("http://localhost:4200").allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
    }
}
