package keepitbusy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoreTesting {

    @Test
    public void testMod(){
        double a = 5/10;
        System.out.println(a);

        double a1 = (double)5/10;
        System.out.println(a1);

        double a2 = 5d/10d;
        System.out.println(a2);

        double a3 = 5.0/10.0;
        System.out.println(a3);

        int b = 15%10;
        System.out.println(b);
    }

    @Test
    public void testMod109(){
        int mod = 100000^100000; //(10^9)+7; XOR
        System.out.println(mod);

        mod = 1000000007;
        int a = 2147234873;
        int b = 214798734;
        System.out.println(a+b);
        System.out.println((a+b)%mod);

        int c = ((a%mod)+(b%mod))%mod;
        System.out.println("last: "+c);
        System.out.println(a%mod);
        System.out.println(b%mod);
    }

    @Test
    public void testUBS(){
        List<Integer> list = Arrays.asList(3,4,5,3,5,2);


        Integer[] arr = list.toArray(Integer[]::new);
        //System.out.println(arr.toString());

        int numOfShares = 0;
        long total = 0;
        int n = arr.length;
        List<Long> profit = new ArrayList<>();

        for(int i=0; i<n-1; i++){

            if(arr[i] < arr[i+1]){
                //buy
                numOfShares++;
                total = total - arr[i];
            }else{
                //sell
                profit.add(total+(arr[i]*numOfShares));
                numOfShares = 0;
                total = 0;
            }
        }
        System.out.println("last.."+profit.size());

        profit.forEach(l -> System.out.println(l.intValue()));
        //profit.stream().map(Long::intValue).collect(Collectors.toList())
    }


    @Test
    public void testStocks(){
        List<Integer> list = Arrays.asList(3,4,5,3,5,2);
        list = Arrays.asList(1,2,100);
        //Integer[] arr = list.toArray(Integer[]::new);
        int arr[] = {1,2,100};
        int n = arr.length;
        int res = maxProfit(arr, 0, n-1);
        System.out.println("RESULT: "+res);
    }

    private int maxProfit(int[] arr, int start, int end) {
        System.out.println("Finding profit with i:"+start+" j:"+end);
        if (end <= start)
            return 0;
        int profit  = 0;

        //buy time
        for (int i=start; i<end; i++){
            //sell time
            for (int j= i+1; j<=end; j++){

                if(arr[j] > arr[i]){
                    //numOfStocks++;
                    int currProfit = (arr[j] - arr[i]);
                    int left = maxProfit(arr, start, i);
                    System.out.println("Left: "+left);
                    int right = maxProfit(arr, j, end);
                    System.out.println("Right: "+right);
                    currProfit = currProfit + left + right;
                    System.out.println("Curr profit : "+currProfit+ " with "+arr[i]+", "+arr[j]);

                    profit = Math.max(profit, currProfit);
                    //System.out.println("profit: "+profit);
                }

            }
        }
        return profit;
    }


    @Test
    public void basicInc(){
        int c = 2;
        int d = c++;
        System.out.println("c: "+c);
        System.out.println("d: "+d);
        Map<Integer, Integer> map = new HashMap<>();

    }

}
