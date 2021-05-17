package hu.maven.Components.Model;

import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.ArrayList;

/**
 * Ez a {@link Class} felelős a játék logikájának és szabályainak betartatásáért.
 */
public class Board{

    private Turn player;

    /**
     * A pálya mérete.
     */
    public static int BOARD_SIZE = 4;

    private ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
    private ArrayList<Position> selected;

    /**
     * Ez a {@link java.lang.reflect.Constructor} létrehozza az asztal mátrixát  BOARD_SIZE alapján, illetve a létrehozza a
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
     *Ez a {@link java.lang.reflect.Method} a selected változót tölti fel a {@link Position}-t.
     * @throws IllegalArgumentException , ha az adott {@link Position} már szerepel benne.
     * @param position pozíció
     */
    public void addSelected(Position position) {
        for (var p : selected) {
            if(p.equals(position)) {
                throw new IllegalArgumentException();
            }
        }

        selected.add(position);
    }

    /**
     *Ez a {@link java.lang.reflect.Method} eltávolítja a selected-ben szereplő adott pozíciót.
     * @param position pozíció
     */
    public void removeSelected(Position position) {
        for (var p: selected) {
            if(p.equals(position)) {
                selected.remove(position);
                return;
            }
        }
    }
    /**
     *Ez a {@link java.lang.reflect.Method} egy másolatot készít a selectedről.
     * @return {@link ArrayList} a selected másolata
     */
    public ArrayList<Position> getSelected() {
        return (ArrayList<Position>) selected.clone();
    }

    /**
     *Ez a {@link java.lang.reflect.Method} clear-eli a selected-et.
     */
    public void resetSelected() {
        selected.clear();
    }

    /**
     *Ez a {@link java.lang.reflect.Method} azért felel,hogy a 2 játékos között megtörténjen a váltás.
     * @return {@link Turn}-höz megfelelő playert.
     */
    public Turn changePlayer() {
        this.player = this.player.otherPlayer();
        return this.player;
    }
    /**
     *Ez a {@link java.lang.reflect.Method} a pálya állapotáról ad vissza egy sztringet.
     * @return {@link String}
     */
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

    /**
     *A {@link java.lang.reflect.Method} nézi meg, hogy a selected elemek egy sorban vagy oszlopban vannak-e.
     * @return {@link Boolean} igaz/hamis
     */
    public boolean isSameRowOrCol(){
        if(selected.size() == 1) {
            return true;
        } else if(selected.size()<1 || selected.size()>4){
            return false;
        } else {
            return isSameCol() || isSameRow();
        }
    }

    /**
     * A {@link java.lang.reflect.Method} ellenőrzi,hogy egy sorban vannak-e az elemek.
     * @return {@link Boolean} igaz/hamis
     */
    public boolean isSameRow() {
        var rows = new ArrayList<Integer>();

        for(var p: selected){
            rows.add(p.row());
        }

        return rows.stream()
                .allMatch(rows.get(0)::equals);
    }

    /**
     * A {@link java.lang.reflect.Method} ellenőrzi,hogy egy oszlopban vannak-e az elemek.
     * @return {@link Boolean} igaz/hamis
     */
    public boolean isSameCol(){
        var cols = new ArrayList<Integer>();
        for(var p: selected){
            cols.add(p.col());
        }

        return cols.stream()
                .allMatch(cols.get(0)::equals);
    }
    /**
     *Ez a {@link java.lang.reflect.Method} az aktuális player lekérdezésére szolgál.
     * @return {@link Turn} player
     */
    public Turn getPlayer() {
        return player;
    }
}
