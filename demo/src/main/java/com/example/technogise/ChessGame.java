package com.example.technogise;

public class ChessGame {

    public static void main(String[] args) {

        String input = "King D5";
        Board board = new Board(8, 8);
        board.init();;
        King king = new King();

        String[] piece = input.split(" ");
        if(piece[0].equals(ChessPiece.KING.name())){
           //king.possibleMoves()
        }


    }
}
