package basic.example;

import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboSumI {

    private static List<List<Integer>> result = new ArrayList<>();
    public static void main(String[] args) {
        int arr[]  = {2,3,6,7};
        ComboSumI obj = new ComboSumI();
        obj.combinationSum(arr, 7);
    }


    public List<List<Integer>> combinationSum(int[] nums, int target) {
        Arrays.sort(nums);
        backtrack(new ArrayList<>(), nums, 0, target);
        return result;
    }

    private void backtrack(List<Integer> candidate, int[] nums, int start, int target){
        //base condition
        if(target < 0)
            return;
        if(target == 0){
            result.add(candidate);
            return;
        }

        for(int i=start; i<nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue;
            candidate.add(nums[i]);
            backtrack(candidate, nums, i, target-nums[i]);
            candidate.remove(candidate.size()-1);
        }
    }
}
