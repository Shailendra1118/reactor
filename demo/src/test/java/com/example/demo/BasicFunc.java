package com.example.demo;

import org.junit.Test;

import javax.swing.event.ChangeEvent;
import java.util.function.IntBinaryOperator;
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


}
