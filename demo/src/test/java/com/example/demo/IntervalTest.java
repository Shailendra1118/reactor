package com.example.demo;

import java.util.*;

public class IntervalTest {

    static int [][] intervals = {{2,6}, {3,5}, {1,5}, {8,10}, {9,18}};
    public static void main(String[] args) {
        //int[][] intervals; // = new int[5][2]; new int[][] is necessary in new line before init array
        List<Interval> input = new ArrayList<>();
        for(int i=0; i<intervals.length; i++){
            input.add(new Interval(intervals[i][0], intervals[i][1]));
        }
       // input.forEach(e-> System.out.println(e));
       // Collections.sort(input, Comparator.comparingInt(i -> i.start));
        input.sort(Comparator.comparingInt(i -> i.start));
       System.out.println("Post sorting...");
       input.forEach(e-> System.out.println(e));
        System.out.println("------------");

        List<Interval> res = new ArrayList<>();  // List can be made of int[]
        // working on sorted list
        int start = input.get(0).start;
        int end = input.get(0).end;
        for(int i=1; i<input.size(); i++){
           if(end >= input.get(i).start){
               end = Math.max(end, input.get(i).end);
           }else{
               //found disjoint one, add on-going interval
               res.add(new Interval(start, end));
               start = input.get(i).start;
               end = input.get(i).end;
           }
        }
        res.add(new Interval(start, end));
        System.out.println("Postprocessing------");
        res.forEach(e-> System.out.println(e));
        int out[][] = Interval.getArray(res);
        System.out.println(Arrays.deepToString(out)); // Array of arrays
    }



    static class Interval {
        int start;
        int end;
        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
        static int[][] getArray(List<Interval> list){
            int [][] res = new int[list.size()][2];
            int idx = 0;
            for(Interval i : list){
                res[idx][0] = i.start;
                res[idx][1] = i.end;
                idx++;
            }
            return res;
        }

        public String toString(){
            return this.start+","+this.end;
        }
    }
}
