package com.example.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.junit.Test;
import reactor.core.publisher.Mono;

import javax.swing.event.ChangeEvent;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BasicFunc {

    @Test
    public void testSome(){
        String a = "abc";
        String b = "bac";
        int xor = 0;
        for(char c : b.toCharArray()){
            xor = xor ^ c;
        }

        int ans = a.chars().boxed().mapToInt(Integer::intValue).reduce(0, (x, y) -> x^y);
        System.out.println(ans);

    }

    class Employee {
        public void print(Object obj){
            System.out.println("Array");
        }

        public void print(String str){
            System.out.println("str");
        }
    }

    @Test
    public void testOne(){
        Employee e = new Employee();
        e.print(null);
        int i = 10;
        int []a[] = new int[5][];
        int b[] = {1,2,3};
        //System.out.println(b[i = i++]);
        System.out.println(a.length);
        System.out.println(a[0].length);
    }


    private Predicate<String> refundInvoiceFilter = (str) -> {
      if(str.equals("toPass"))
          return true;
      else return false;

    };

    @Test
    public void testFunc(){
        Mono.just("toPassl")
                .flatMap(str -> {
                    System.out.println("Rec - "+str);
                    return Mono.just(str);
                }).filter(str -> refundInvoiceFilter.test(str))
                .doOnDiscard(String.class, str -> System.out.println("first discarded"))
                .flatMap(str -> {
                    System.out.println("post filter");
                    return Mono.just(str);
                })
                .doOnDiscard(String.class, str -> System.out.println("discarded"))
                .subscribe(s -> System.out.println("pass"), f -> System.out.println("fail"));
    }




}
