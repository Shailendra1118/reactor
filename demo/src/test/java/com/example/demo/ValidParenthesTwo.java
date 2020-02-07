package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ValidParenthesTwo {

    public static void main(String[] args) {
        List<String> res = new ArrayList<>();
        int n = 3;
        backtrack(res, "", 0, 0, n);
        System.out.println(res);
        LinkedList<String> l = new LinkedList<>();
    }

    private static void backtrack(List<String> res, String s, int open, int close, int max) {
        if(s.length() == 2*max){
            System.out.println("Adding.."+s);
            res.add(s);
            return;
        }
        if(open < max){
            System.out.println("open: "+open + " close: "+close);
            System.out.println("Calling bt ( -> with "+s+"(");
            backtrack(res, s+"(", open+1, close, max);
        }
        if(close < open){
            System.out.println("open: "+open + " close: "+close);
            System.out.println("Calling bt )-> with "+s+")");
            backtrack(res, s+")", open, close+1, max);
        }
        System.out.println("Retunring...");
    }
}
