package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Leeting {

    @Test
    public void test1(){
        int arr[] = {1,2};
        int res[] = callIt();
        Arrays.sort(res);
        Arrays.stream(res).forEach(i -> System.out.println(i));

    }

    private int[] callIt() {
        int arr[] = {12,4,-8};
        return arr;
    }

    @Test
    public void testSum3(){
        int input[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = sum3(input);
        System.out.println(res);
    }

    private List<List<Integer>> sum3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //first sort the input array
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int l = 0;
        int h = nums.length-1;
        List<Integer> ans;
        for(int i=0; i<nums.length-2; i++){
            l = i+1;
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                while (l < h) {
                    if (nums[l] + nums[h] + nums[i] == 0) {
                        ans = new ArrayList<>();
                        ans.add(nums[l]);
                        ans.add(nums[h]);
                        ans.add(nums[i]);
                        res.add(ans);
                        //increment lower point
                        while(l<h && nums[l] == nums[l+1]){
                            l++;
                        }
                        while(l<h && nums[h] == nums[h-1]){
                            h--;
                        }
                        l++;
                        h--;
                    } else {
                        if (nums[l] + nums[h] + nums[i] > 0) {
                            h--;
                        } else {
                            l++;
                        }
                    }
                }
            }
        }
        return res;
    }


    @Test
    public void testSumTwo(){
        int input[] = {-3,4,3,90};
        int res[] = twoSum(input, 0);
        System.out.println(Arrays.toString(res));
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i );
        }
        return result;
    }

    @Test
    public void testAna(){
        boolean res = isAnagram("cab", "bar");
        System.out.println("Res: "+ res);
    }


    @Test
    public void anagram(){
        String[] strs = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        List<List<String>> res = new ArrayList<>();
        boolean found = false;
        for(int i=0; i<strs.length; i++){
            found = false;
            String cur = strs[i];
            for(int j=0; j< res.size(); j++){
                if(isAnagram(cur, res.get(j).get(0))){
                    res.get(j).add(cur);
                    found = true;
                    break;
                }
            }
            if(! found){
                List<String> sub = new ArrayList<>();
                sub.add(cur);
                res.add(sub);
            }

        }

        res.stream().forEach(list -> {
            System.out.println();
            list.stream().forEach(str -> System.out.print(str+" "));

        });
    }

    private boolean isAnagram(String first, String sec){
        int i=0, j=first.length();
        if(first.length() != sec.length()){
            return false;
        }

        char[] cFirst = first.toCharArray();
        char[] cSec = sec.toCharArray();
        Arrays.sort(cFirst);
        Arrays.sort(cSec);
        System.out.println(cFirst);
        System.out.println(cSec);

        while(i < j){
            if(cFirst[i] != cSec[i]){
                return false;
            }
            i++;
        }
        return true;

    }

    @Test
    public void testMod(){
        int res = -10%2;
        int res1 = 10%2;
        System.out.println(res);
        System.out.println(res1);
    }

    @Test
    public void test3Sum(){
        int[] input = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(input);
        System.out.println(res);
        // [[-1,-1,2],[-1,0,1]]
        List<Integer> list = Arrays.stream(input).boxed().collect(Collectors.toList());

    }

    private List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        List<Integer> subList;

        for(int i=0; i<n-1; i++){
            Set<Integer> set = new HashSet<>();
            //System.out.println(set.toArray().toString());
            set.forEach(item -> System.out.println(item+" "));
            for(int j=i+1; j<n; j++){
                int sumCurr = nums[i] + nums[j];
                if( sumCurr != 0 && set.contains(-sumCurr)){
                    subList = new ArrayList<>();
                    subList.add(nums[i]);
                    subList.add(nums[j]);
                    subList.add(-sumCurr);
                    res.add(subList);
                }else{
                    set.add(nums[j]);
                }
            }
        }

        return res;
    }

    @Test
    public void testBasic(){
        Basic basic = new Basic();
        if(basic.getIsEnabled()){
            System.out.println("Enabled");
        }else{
            System.out.println("Disabled");
        }
    }

    class Basic{
        private Boolean isEnabled = true;
        public Boolean getIsEnabled(){
            return this.isEnabled;
        }
    }

    @Test
    public void testDivide(){
        int a = 3/2;
        System.out.println("A is: "+a);
        int b = 3%2;
        System.out.println("B is: "+b);


        List<String> list = null;
        for(String val : list){
            System.out.println(val);
        }
    }
}
