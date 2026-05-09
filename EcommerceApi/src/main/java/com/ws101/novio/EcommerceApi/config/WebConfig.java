package com.ws101.novio.EcommerceApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for CORS settings.
 *
 * Configures Cross-Origin Resource Sharing (CORS) to allow
 * the frontend to communicate with the backend API.
 *
 * @author Novio, Mariel Kimberly B.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings to allow frontend access.
     *
     * Allows requests from the Live Server origin on port 5500
     * for all API endpoints with the required HTTP methods and headers.
     *
     * @param registry the CorsRegistry to add mappings to.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5500",
                        "http://127.0.0.1:5500"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type", "X-CSRF-TOKEN")
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true);
    }
}