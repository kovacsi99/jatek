package hu.maven.Components.Model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Board{

    public static int BOARD_SIZE = 4;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.ROCK);
            }
        }
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(int i, int j) {
        return board[i][j].get();
    }

    public void move(int i, int j) {
        board[i][j].set(
                switch (board[i][j].get()) {
                    case ROCK, NONE -> Square.NONE;
                }
        );
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new Board();
        System.out.println(model);
    }

}



