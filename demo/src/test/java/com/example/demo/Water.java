package com.example.demo;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Water {

    public static void main(String[] args) {
        Water obj = new Water();
        int arr[] = {3, 0, 0, 2, 0, 4};
        int res = obj.maxArea(arr);
        System.out.println("Res: "+res);
        int res1 = obj.maxAreaOne(arr);
        System.out.println("ResOne: "+res);
    }

    private int maxAreaOne(int[] arr) {
            int n = arr.length;
            int left=0; int right=n-1;
            int res=0;
            int maxleft=0, maxright=0;
            while(left<=right){
                if(arr[left] <= arr[right]){
                    if(arr[left] >= maxleft)
                        maxleft = arr[left];
                    else
                        res+=maxleft-arr[left];
                    left++;
                }
                else{
                    if(arr[right]>=maxright)
                        maxright= arr[right];
                    else
                        res+=maxright-arr[right];
                    right--;
                }
            }
            return res;
    }

    private int maxArea(int[] height) {
        int n = height.length;
        int res = 0;
        int left[] = new int[n];
        int right[] = new int[n];

        //caculate highest bar on left
        left[0] = height[0];
        for(int i=1; i<n; i++){
            left[i] = Math.max(left[i-1], height[i]);
        }
        Arrays.stream(left).forEach(e -> System.out.print(e+" "));

        right[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--){
            right[i] = Math.max(right[i+1], height[i]);
        }
        System.out.println("Right: ");
        Arrays.stream(left).forEach(e -> System.out.print(e+" "));

        for(int i=0; i<n ;i++){
            res = res + (Math.min(left[i], right[i]) - height[i]);
        }
        System.out.println(res);
        return res;

    }


}
