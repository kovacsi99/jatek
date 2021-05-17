package hu.maven.Components.Model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Pos;
import hu.maven.Components.Controller.BoardGameController;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Ez az osztály felelős a játék logikájának és szabályainak betartatásáért.
 */
public class Board{

    private Turn player;

    public static int BOARD_SIZE = 4;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
    private ArrayList<Position> selected;

    /**
     * Létrehozza az asztal mátrixát {@code BOARD_SIZE} alapján, illetve a létrehozza a
     * selected változót,amiben majd a kijelölt elemek pozícióját fogjuk tárolni.
     */
    public Board() {
        player = Turn.PLAYER1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.ROCK);
            }
        }
        selected = new ArrayList<Position>();
    }

    /**
     * A selected változót tölti fel a kijelölt elemek pozíciójával.
     * @param position
     */
    public void addSelected(Position position) {
        for (var p : selected) {
            if(p.equals(position)) {
                throw new IllegalArgumentException();
            }
        }

        selected.add(position);
    }

    public void removeSelected(Position position) {
        for (var p: selected) {
            if(p.equals(position)) {
                selected.remove(position);
                return;
            }
        }
    }

    public ArrayList<Position> getSelected() {
        return (ArrayList<Position>) selected.clone();
    }

    public void resetSelected() {
        selected.clear();
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
    public void changePlayer() {
        this.player = this.player.otherPlayer();
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
    public boolean isValidSelection(){
        if(selected.size()<1 || selected.size()>4){
            return false;
        }
        var rows = new ArrayList<Integer>();
        var cols = new ArrayList<Integer>();
        for(var p: selected){
            rows.add(p.row());
            cols.add(p.col());
        }

        Boolean sameRow = rows.stream()
                .allMatch(rows.get(0)::equals);

        Boolean sameCols =   cols.stream()
                .allMatch(cols.get(0)::equals);

        if (!(sameRow || sameCols)) {
            return false;
        }

        if(sameRow) {
            selected.sort(Comparator.comparingInt(Position::col));
        } else {
            selected.sort(Comparator.comparingInt(Position::row));
        }

        for(int i = 1;i < selected.size(); i++){
            if(!selected.get(i).isNeighbour(selected.get(i-1)))
                return false;

        }
        return true;
    }


    public static void main(String[] args) {
        var model = new Board();
        System.out.println(model);
    }

}

