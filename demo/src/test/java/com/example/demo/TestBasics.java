package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBasics {

    enum TestEnum {
        PAID("PAID"),
        VOIDED("VOIDED");
        String value;
        TestEnum(String value){
            this.value = value;
        }
    }


    @Test
    public void testIt(){
        Boolean condition = null;
        String val = condition == true ? "%" : "3e";
        System.out.println(val);
    }


    @Test
    public void testOver(){
        TestBasics obj = new TestBasics();
        obj.print(new Object());
    }

    private void print(Object obj) {
        System.out.println("Calling object");
        System.out.println(obj);
    }

    private void print(String obj) {
        System.out.println("Calling string");
        System.out.println(obj);
    }

    class Parent {
        private String place = "ParentPlace";
    }

    class Child extends Parent {
        private String place = "ChildPlace";
    }

    class Child1 extends Parent {
        private String place = "ChildOnePlace";
    }


    @Test
    public void testMod(){
        float a = (float)5/10;
        int b = 5%10;
        System.out.println("a "+a);
        System.out.println("b "+b);

        int z = 10/0;
        System.out.println(a);
    }


}
