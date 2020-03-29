package basic.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Temp {

    public static void main(String[] args) {
        int[] res = sumZero(6);
        System.out.println(Arrays.toString(res));

        int or[] = {1,2,3,4,0,0,0};
        int arr[] = Arrays.copyOfRange(or, 0, 4);
        System.out.println(Arrays.toString(arr));
        List<Integer> list = new ArrayList<>();
        IntStream.of(arr).forEach(i -> list.add(i));
        System.out.println(list.toString());


    }

    public static int[] sumZero(int n) {
        int arr[] = new int[n];
        int sum = 0;
        for(int i = 0; i < n-1; i++) {
            arr[i] = i+1;
            sum += arr[i];
        }
        arr[n-1] = -sum;
        return arr;
    }
}
