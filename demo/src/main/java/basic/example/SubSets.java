package basic.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSets {

    static List<List<Integer>> superSet = new ArrayList<>();
    public static void main(String[] args) {
        int arr[] = {1,2,3};
        subsets(arr, 0, new ArrayList<Integer>());

        for(List<Integer> list : superSet){
            for(int i : list){
                System.out.print(i+" ");
            }
            System.out.println();
        }

    }

    private static void subsets(int[] arr, int start, List<Integer> subset) {
        System.out.println("Got: "+ subset.toString()+ " with start: "+start);
        superSet.add(subset);
        for(int i=start; i<arr.length; i++){
            subset.add(arr[i]);
            System.out.println("Calling after adding: "+arr[i]);
            subsets(arr, i+1, new ArrayList<>(subset));
            System.out.println("removing: "+(subset.get(subset.size()-1)));
            subset.remove(subset.size()-1);
        }
    }
}
