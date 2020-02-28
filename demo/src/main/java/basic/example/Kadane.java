package basic.example;

public class Kadane {
    public static void main(String[] args) {
        int arr[] = {-2,-3,4,-1,-2,1,5,-3};
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int num: arr){
            curSum += num;
            if(curSum < 0){
                curSum = 0;
            }else
                maxSum = Math.max(maxSum, curSum);
        }
        System.out.println("Res: "+maxSum);
    }
}
