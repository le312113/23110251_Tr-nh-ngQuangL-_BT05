package com.BT10.Config;
import com.BT10.Interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RoleInterceptor roleInterceptor() {
        return new RoleInterceptor(); // Tạo một bean của Interceptor
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor())
                .addPathPatterns("/admin/**", "/user/**");  // Áp dụng cho các đường dẫn admin và user
    }
}
