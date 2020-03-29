package basic.example;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TestOptima {

    public static void main(String[] args) {
        int MOD=1000000007;
        // Write your code here
        long n = 34534; //455070581
        List<Long> bigBinary = new ArrayList<>();
        for(long i=1; i<=n; i++){
            //convert to binary
            long bin[] = convertoBinary(i);
            //System.out.println("Binary: "+ Arrays.toString(bin));
            LongStream.of(bin).forEach(num -> bigBinary.add(num));
        }

        //covert to decimal
        double total = 0;
        long power = 0;
        for(long i : bigBinary){
            if(i != 0){
                 total = (total + Math.pow(2, power))%MOD;
                 //total = total%MOD;
            }
            power++;
        }
        System.out.println("Total: "+total%MOD);

    }

    private static long[] convertoBinary(long n){
        long binary[] = new long[99];
        int index = 0;
        while(n > 0){
            binary[index] = n%2;
            index = index + 1;
            n = n/2;
        }
        //System.out.println(Arrays.toString(binary));
        //int []res = Arrays.copyOfRange(binary, 0, index);
        //return res;
        int j = 0;
        long res[] = new long[index];
        for(int i = index-1; i>=0; i--) {
            res[j++] = binary[i];
        }
        return res;

    }

}
