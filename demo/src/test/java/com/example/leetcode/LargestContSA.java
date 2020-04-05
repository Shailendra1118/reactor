package com.example.leetcode;

import java.util.Arrays;

public class LargestContSA {


    /**
     * WRONG implementation
     * TODO -
     * 1. Think and validate your solution first before even writing a single line of code
     * Here If you sort the array it will mess up the positioning of sub-array
     * @param args
     */
    public static void main(String[] args) {
        int arr[] =  {3, 4, 6, 1, 2};
                // {10, 12, 12, 10, 10, 11, 10};
        Arrays.sort(arr); // FAIL ekdum FAIL
        System.out.println("sorted:"+Arrays.toString(arr));
        int max = 0;
        boolean found = false;
        for(int i=0; i<arr.length; i++){
            int diff = 1;
            int j;
            for(j = i+1; j<arr.length; j++){
                if(arr[j] - arr[i] == diff){
                    diff++;
                    found = true;
                }else{
                    break;
                }
            }
            if(found){
                max = Math.max(max, diff);
                found = false;
                i = j-1;
            }

        }

        System.out.println("Max="+max);
    }
}
