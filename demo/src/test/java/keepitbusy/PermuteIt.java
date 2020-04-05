package keepitbusy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PermuteIt {

    @Test
    public void testPermute(){

        String s = "ABC";
        char[] arr = s.toCharArray();
        List<String> res = permute(arr, 0, new ArrayList<>());
        System.out.println(res.toString());
    }

    private List<String> permute(char[] arr, int index, List<String> out) {
        if(index == arr.length-1){
            out.add(String.valueOf(arr));
            return out;
        }
        for(int i = index; i<arr.length; i++){
            swap(arr, index, i);
            permute(arr, index+1, out);
            swap(arr, i, index);
        }
        return out;
    }

    private void swap(char[] arr, int i, int index) {
        char t = arr[i];
        arr[i] = arr[index];
        arr[index] = t;
    }
}
