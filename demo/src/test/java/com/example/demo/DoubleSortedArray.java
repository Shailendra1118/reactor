package com.example.demo;

import java.util.Arrays;

public class DoubleSortedArray {
    public static void main(String[] args) {
        int arr[] = {1,1,1,2,2,3};
        int res = removeDuplicates(arr);
        System.out.println("Res: "+res);
    }

    public static int removeDuplicates(int[] nums) {
        int index = 0;
        int l = 0;
        int r = 0;
        while (l < nums.length) {
            while (r < nums.length && nums[r] == nums[l]) {
                if (r - l < 2) {
                    nums[index++] = nums[r];
                    System.out.println("After index update: "+ Arrays.toString(nums));
                    System.out.println("l:"+l+" r:"+r+" index: "+index);
                }
                System.out.println("Increment R");
                r++;
            }
            System.out.println("Assign R to L "+r+"->"+l);
            l = r;
        }
        return index;
    }
}
