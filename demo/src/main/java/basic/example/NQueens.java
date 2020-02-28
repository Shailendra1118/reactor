package basic.example;

import java.util.Arrays;

public class NQueens {

    static int N = 4;
    // TIME - 11:20
    public static void main(String[] args) {

        int[][] arr = new int[N][N];
        boolean res = solveIt(arr, 0);
        System.out.println("Result: "+res);
        System.out.println(Arrays.deepToString(arr));
        printSolution(arr);
    }

    static void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }

    private static boolean solveIt(int[][] arr, int col) {
        if(col >= N){
            return true;
        }else{
            // Consider this column and try placing this queen in all rows one by one
            for(int i=0; i<N; i++){
                if(isValid(arr, i, col)){
                    arr[i][col] = 'Q';
                    if(solveIt(arr,col+1)){
                        return true;
                    }
                    arr[i][col] = 0;
                }
            }
        }
        // If the queen can not be placed in any row in this col, then return false
        return false;
    }

    private static boolean isValid(int[][] arr, int x, int y) {
        //check left only, no right side placed yet or top or down
        for(int j=0; j<y; j++){
           if(arr[x][j] != 0){
               return false;
           }
        }
        //check North-West
        for(int i=x-1,j=y-1; i>=0 && j>=0; i--, j--){
            if(arr[i][j] != 0){
                return false;
            }
        }
        //check South-West
        for(int i=x+1,j=y-1; i<N && j>=0; i++, j--){
            if(arr[i][j] != 0){
                return false;
            }
        }
        return true;
    }
}
