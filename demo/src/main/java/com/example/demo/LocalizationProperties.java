package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
@Configuration
@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "invoices")
@PropertySource("classpath:locales.properties")
public class LocalizationProperties {
    private String party;
    //private Map<String, LocaleValue> localizedValueMap;

    public String getParty(){
        return this.party;
    }

    public void setParty(String party){
        this.party = party;
    }

    /*public static class LocaleValue {
        private String value;

        public String getValue(){
            return this.value;
        }
    }*/
}
