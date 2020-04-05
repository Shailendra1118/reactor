package com.example.technogise;

import org.junit.Test;

import java.util.Arrays;

public class Board {


    @Test
    public void populateBoard(){

        int ROW = 8;
        int COL = 8;
        String board[][] = new String[ROW][COL];
        int inc = 0;
        char ch = 'A';
        for(int i=0; i<COL; i++){
            int idx = 8;
            for (int j=0; j<ROW; j++){
                //System.out.println("CH: "+Character.valueOf(ch).charValue());
                String val = String.valueOf(ch).concat(String.valueOf(idx));
                //System.out.println("val: "+val);
                board[j][i] = val;
                idx--;
            }
            inc++;
            ch += inc;

        }

        System.out.println(Arrays.deepToString(board));
    }


    @Test
    public void basic(){
        char a = 'A';
        char b = (char)'A'+1;
        System.out.println(b);
    }
}


