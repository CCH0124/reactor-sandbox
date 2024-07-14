package org.annotation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Vehicle.class)
public class Config {
    @Bean
    public Brand getBrand() {
        return new Brand("volvo", 1844);
    }
}
