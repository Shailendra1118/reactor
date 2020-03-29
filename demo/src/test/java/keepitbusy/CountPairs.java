package keepitbusy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CountPairs {

    public static void main(String[] args) {
        int arr[] = {1,1,1,1};
        int sum = 2;
        Map<Integer,Integer> map = new HashMap<>();
        IntStream.of(arr).forEach(i-> {
            if(! map.containsKey(arr[i])){
                map.put(arr[i], 1);
            }else
                map.put(arr[i], map.get(arr[i])+1);
        });

        System.out.println(map.toString());
        int count =0;
        for (int i=0; i<arr.length; i++){
            if(map.containsKey(sum-arr[i])){
                System.out.println("increasing count");
                count = count + map.get(sum-arr[i]);
            }

            /*if(sum-arr[i] == arr[i]) {
                System.out.println("decreasing...");
                count--;
            }*/
        }

        System.out.println("Res: "+ count);
        int nums[] = {3,1,4,1,5};
        int res = findPairs(nums, 2);
        System.out.println("Count: "+res);
    }


    public static int findPairs(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i])+1);
            }else{
                map.put(nums[i], 1);
            }
        }
        System.out.println(map.toString());

        //count
        int count = 0;
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(k-nums[i])){
                count = count + map.get(k-nums[i]);
                System.out.println("count after - for i="+i+" and count="+ count);
            }
            /*if(map.containsKey(k+nums[i])){
                count = count + map.get(Math.abs(k+nums[i]));
                System.out.println("count after + for i="+i+" and count="+ count);
            }*/

            if(k-nums[i] == nums[i]){
                System.out.println("minus one...");
                count--;
            }

        }
        return count/2;

    }
}
