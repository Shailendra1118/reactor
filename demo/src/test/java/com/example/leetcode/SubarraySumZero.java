package com.example.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SubarraySumZero {
    public static void main(String[] args) {
        int arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
        int res = findLargestSA(arr);
        System.out.println("Res: "+res);
        res = findLargestSABetter(arr);
        log.info("Res={}", res);
    }

    private static int findLargestSA(int[] arr) {
        //find the longest subarray whose sum is zero
        int maxLength = 0;
        //int total = 0;
        for(int i=0; i<arr.length; i++){
            //fix starting point
            //int total = arr[i];
            int total = 0;
            for(int j=i; j<arr.length; j++){
                total = total + arr[j];
                if(total == 0){
                    int currLength = j-i+1;
                    log.info("CurrLength={} with i={} j={}",currLength, i, j);
                    maxLength = Math.max(maxLength, currLength);
                }
            }
        }
        return maxLength;
    }

    private static int findLargestSABetter(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            if (map.containsKey(total)) {
                int oldIdx = map.get(total);
                if (i - oldIdx > max) {
                    max = i - oldIdx;
                }
            } else {
                map.put(total, i);
            }

        }
        return max;
    }
}
