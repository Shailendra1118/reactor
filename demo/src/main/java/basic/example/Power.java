package basic.example;

public class Power {

    public static void main(String[] args) {
        int steps = 4;
        // 2^(n-1)
        int val = 1 << (steps-1);
        System.out.println(val);
        val = recursive(steps);
        int res;
        System.out.println("Recursive"+(res = val));
    }

    private static int recursive(int steps) {
        if(steps == 0 || steps == 1)
            return 1;
        else{
            int leaps = 0;
            for(int i = 0; i<steps; i++){
                leaps = leaps + recursive(i);
            }
            return leaps;
        }

    }
}
