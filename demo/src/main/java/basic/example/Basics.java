package basic.example;

public class Basics {

    public static void main(String[] args) {
        String str = "aab";
        recPermute("", str);
        //recFixedOne(str.toCharArray(), 0, str.length()-1);
    }

    private static void recFixedOne(char arr[], int s, int e) {
        if(s == e){
            System.out.println(String.valueOf(arr));
            return;
        }else {
            for (int i = s; i <= e; i++) {
                swap(arr, i, s);
                recFixedOne(arr, s+1, e);
                swap(arr, s, i);
            }
        }
    }

    private static void swap(char[] arr, int s, int i) {
        char temp = arr[s];
        arr[s] = arr[i];
        arr[i] = temp;
    }

    private static void recPermute(String soFar, String rest) {
        if(rest.length() == 0){
            System.out.println("res is zero: "+ soFar);
            return;
        }else{
            boolean[] count = new boolean[26];
            for (int i=0; i< rest.length(); i++){
                char c = rest.charAt(i);
                String next = soFar + c;
                String remaining = rest.substring(0, i) + rest.substring(i+1);
                if(count[c - 'a'] == false){
                    //System.out.println("Not true, so calling rec: "+ (c-'a'));
                    recPermute(next, remaining);
                }
                //System.out.println("setting true:"+ (c-'a'));
                count[c-'a'] = true;

            }

        }
    }


}
