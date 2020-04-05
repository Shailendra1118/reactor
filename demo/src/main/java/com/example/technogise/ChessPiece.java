package com.example.technogise;

public enum ChessPiece {

    KING("King"),
    QUEEN("Queen"),
    KNIGHT("Knight");

    private String type;

    ChessPiece(String type) {
        this.type = type;
    }
}
