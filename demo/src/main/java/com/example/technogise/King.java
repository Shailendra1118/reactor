package com.example.technogise;


public class King {

    private String name;
    private int moves[][] = {{0,-1},{-1,-1},{-1,1}, {0,1},{1,1},{1,0}};

    public int[][] possibleMoves(String position){
        return moves;

    }



}
