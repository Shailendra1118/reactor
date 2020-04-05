package com.example.technogise;

import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private int ROW;
    private int COL;
    String board[][] = new String[ROW][COL];

    public Board(int row, int col){
        this.ROW = row;
        this.COL = col;
    }

    private Map<Character, Integer> rowLookup = new HashMap<>();

    public void init(){
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

        //populate map for quick lookup
        rowLookup.put('A', 0);
        rowLookup.put('B', 1);
        rowLookup.put('C', 2);
        rowLookup.put('D', 3);
        rowLookup.put('E', 4);
        rowLookup.put('F', 5);
        rowLookup.put('G', 6);
        rowLookup.put('H', 7);

    }


    public Pair<Integer,Integer> getBoardIndex(String position){
        //find the row number for faster lookup
        char[] val = position.toCharArray();
        int rowIdx = rowLookup.get(Character.valueOf(val[0]));
        for(int i = 0; i<COL; i++){
            if(position.equals(board[rowIdx][i])){
               //find and return pair of indices
            }
        }
        return null;
    }


}
