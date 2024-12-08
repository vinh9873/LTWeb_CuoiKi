package vn.ute.config;

import vn.ute.controller.ProductController;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ProductController.RESOURCE_PATH + "/**")
                .addResourceLocations(new FileSystemResource(ProductController.UPLOAD_DIR + "/"));
    }
}