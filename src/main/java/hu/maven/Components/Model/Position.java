package hu.maven.Components.Model;

/**
 * Ez a {@link Record} számolja ki a kavicsok pozícióját.
 * @param row sor
 * @param col oszlop
 */
public record Position(int row, int col) {

    /**
     *Ez a {@link java.lang.reflect.Constructor}-a a Position osztálynak.
     * @throws IllegalArgumentException , amennyiben nem megfelelő értéket kap.
     * @param row sorok
     * @param col oszlopok
     */
    public Position(int row, int col) {
        if (row < 0 || row >3 || col < 0 || col >3) {
            throw new IllegalArgumentException("Out of range");
        } else {
            this.row = row;
            this.col = col;
        }
    }

    /**
     *Ez a {@link java.lang.reflect.Method} azért felel,hogy létrehozza a játék asztalát.
     * @param that az
     * @return {@link Boolean} igaz/hamis
     */
   public boolean isNeighbour(Position that){
       return Math.abs(this.row - that.row) <= 1 && Math.abs(this.col - that.col) <= 1
               && !(this.row == that.row() && this.col == that.col());
    }

    /**
     *Ez a {@link java.lang.reflect.Method} kiírja sztringbe az oszlopot és sort.
     * @return {@link String}
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }

    /**
     *Ez a {@link java.lang.reflect.Method} ellenőrzi, hogy az objektum egyenlő-e önmagával, illetve,
     * hogy {@link Position} típus-e.
     * @return {@link Boolean}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof Position p) && p.row == row && p.col == col;
    }
}