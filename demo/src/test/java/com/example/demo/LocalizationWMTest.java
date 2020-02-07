package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ValidationAutoConfiguration.class })
@EnableConfigurationProperties({
        LocaleProperties.class,
})
public class LocalizationWMTest {

    @Autowired
    private LocaleProperties localizationProperties;

   @Test
    public void testLocaliztion(){
       System.out.println("TESTSTETESTSTSETETSST");
       System.out.println(localizationProperties.getPaid());
       System.out.println(localizationProperties.getVoided());
   }

   @Test
    public void testStrr(){
       String s = "anagram";
        String t = "nagaram";

           char sChars[] = s.toCharArray();
           char tChars[] = t.toCharArray();
           Arrays.sort(sChars);
           Arrays.sort(tChars);

           if( Arrays.equals(sChars, tChars))
               System.out.println("True");
           else
               System.out.println("False");

   }
}
