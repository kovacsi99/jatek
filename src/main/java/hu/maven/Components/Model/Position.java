package hu.maven.Components.Model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        if (row < 0 || row > 3 || col < 0 || col > 3) {
            throw new IllegalArgumentException("Out of range");
        }else{
            this.row = row;
            this.col = col;
        }
    }


//    public boolean isNeighbour(Position that){
//        return Math.abs(this.row - that.row) <= 1 && Math.abs(this.col - that.col) <= 1
//                && !(this.row == that.row() && this.col == that.col());
//    }

    public boolean isSameRow(Position position) {
        return this.row == position.row;
    }

    public boolean isSameCol(Position position) {
        return this.col == position.col;
    }

    public int row(){
        return this.row;
    }
    public int col(){
        return this.col;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        return (o instanceof Position p) && p.row == row && p.col == col;
    }

}