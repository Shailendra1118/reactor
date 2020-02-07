package com.example.demo;

public class InsertBet {

    public static void main(String[] args) {
        int arr[] = {1,3,5,6};
        int res = findIndex(arr, 7);
        System.out.println("Res:"+res);
    }

    private static int findIndex(int[] arr, int target) {
        if(target == arr[0])
            return 0;
        int i = 0;
        while(i < arr.length){
            if(arr[i] == target)
                return i;
            if((i+1) < arr.length && arr[i] < target && arr[i+1] > target)
                return i+1;
            // increment i
            i++;
        }
        return i;
    }
}
