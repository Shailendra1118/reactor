package keepitbusy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BeautifulLeet {

    @Test
    public void test(){
        int res = countArrangement(5);
        System.out.println("RES: "+res);
    }

    public int countArrangement(int N) {

        int arr[] = new int[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = i;
        }
        // List<List<Integer>> arrangements = getAllArrangements(arr, 0, count);
        Counter c = getAllArrangements(arr, 1, new Counter());
        /*System.out.println(arrangements.toString());
        int count = 0;
        for(List<Integer> list : arrangements){
            if(isBeautiful(list)){
                count++;
            }
        }
        return count;*/
        return c.val;
    }

    private boolean isBeautiful(List<Integer> list){
        Integer arr[] = list.toArray(new Integer[0]);
        boolean invalid = true;
        int N = arr.length;
        for(int i=1; i<=N; i++){
            if(arr[i-1] % i == 0 || i%arr[i-1] == 0){
                invalid = true;
            }else{
                invalid = false;
                break;
            }
        }
        return invalid;
    }

    private Counter getAllArrangements(int arr[], int start, Counter counter){
        if(start == arr.length){
            //List<Integer> sub = Arrays.stream(arr).boxed().collect(Collectors.toList());
            //list.add(sub);
            System.out.println(Arrays.toString(arr));
            counter.val++;
            return counter;
        }

        for(int i=start; i<arr.length; i++){
            swap(arr, i, start);
            if(arr[i] % i == 0 || i%arr[i] == 0)
                getAllArrangements(arr, start+1, counter);
            swap(arr, start, i);
        }
        return counter;
    }

    private void swap(int[] arr, int i, int index) {
        int t = arr[i];
        arr[i] = arr[index];
        arr[index] = t;
    }

    class Counter{
        int val = 0;
    }
}
