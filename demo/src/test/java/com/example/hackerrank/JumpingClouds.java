package com.example.hackerrank;

import org.junit.Test;

import java.util.Arrays;

public class JumpingClouds {

    @Test
    public void minJump(){
        //recursive
        int arr[] = {0, 0, 0, 0, 1, 0};
        int res = findMin(arr, 0, 0);
        System.out.println("Res: "+res);
        int res1 = findMinDP(arr);
        System.out.println("DP: "+res1);
    }

    private int findMinDP(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n+1];
        for(int i=1; i<n; i++){
            if(arr[i] == 0) {
                if(i == 1)
                    dp[i] = dp[i - 1]+1;
                else
                    dp[i] = Math.min(dp[i - 1], dp[i - 2]) + 1;
            }
            else
                dp[i] = Integer.MAX_VALUE;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(dp));
        //dp[i] = Math.min(dp[i-1], dp[i-2])+1;
        return dp[n-1];
    };

    private int findMin(int[] arr, int jump, int idx) {
        int limit = arr.length-2;
        if(idx > limit)
            return jump;
        else{
            int offSet = (idx < limit && arr[idx+2] == 0) ? 2 : 1;
            return findMin(arr, jump+1, idx+offSet);
        }
    }
}
