package com.example.demo;

import java.util.*;

public class ValidParenthese {

    public static void main(String[] args) {
        ValidParenthese obj = new ValidParenthese();

        List<String> res = obj.generateParenthesis(2);
        System.out.println("Answer: "+res);
    }


    public List<String> generateParenthesis(int n) {
        // n is the pair e.g. n =2 () ()
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< n; i++){
            sb.append("()");
        }
        String input = sb.toString();
        System.out.println("Generated parenthese: "+ input);
        Set<String> res = new HashSet<>();
        permute(input.toCharArray(), 0, input.length()-1, res);
        List<String> list = new ArrayList<>();
        list.addAll(res);
        return list;
    }


    private void permute(char arr[], int l, int r, Set<String> res){
        if(l == r){
            if(isValid(arr)){
                //System.out.println(new String(arr));
                System.out.println("isValid: and adding it to list "+new String(arr));
                res.add(new String(arr));

            }
        }

        for(int i=l; i<=r; i++){
            swap(arr, i, l);
            permute(arr, l+1, r, res);
            swap(arr, i, l);
        }
        //return res;
    }

    private boolean isValid(char arr[]){
        Stack<Character> stack = new Stack<>();
        for(char c : arr){
            if(c == '(')
                stack.push(')');
            else if(stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }

    private void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
