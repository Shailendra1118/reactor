package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "status")
@PropertySource("classpath:locale.properties")
public class LocaleProperties {

    //private PAID paid;
    //private VOID voided;
    private Map<String, String> paid;
    private Map<String, String> voided;
}
