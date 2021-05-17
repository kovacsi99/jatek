package hu.maven.Components.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void init(){
        board = new Board();
    }

    @Test
    void addSelected() {
        assertThrows(IllegalArgumentException.class, () -> board.addSelected(new Position(5,5)));
        board.addSelected(new Position(2,2));
        assertEquals(1,board.getSelected().size());
        assertThrows(IllegalArgumentException.class, () -> board.addSelected(new Position(2,2)));
    }

    @Test
    void removeSelected() {
        board.addSelected(new Position(2,2));
        assertEquals(1,board.getSelected().size());
        board.removeSelected(new Position(2, 2));
        assertEquals(0,board.getSelected().size());
        board.resetSelected();
        board.addSelected(new Position(2,2));
        board.addSelected(new Position(1,2));
        board.removeSelected(new Position(1,2));
        assertEquals(1,board.getSelected().size());
    }

    @Test
    void getSelected() {
        assertEquals(board.getSelected(), new ArrayList<Position>());
    }

    @Test
    void resetSelected() {
        board.addSelected(new Position(2,2));
        assertEquals(1,board.getSelected().size());
        board.resetSelected();
        assertEquals(0, board.getSelected().size());
    }

    @Test
    void changePlayer() {
        assertNotEquals(board.getPlayer(), board.changePlayer());
    }

    @Test
    void testToString() {
        assertTrue(board.toString().contains(" "));
        assertTrue(board.toString().contains("\n"));
        assertEquals(36,board.toString().length());
    }

    @Test
    void isSameRowOrCol() {
        board.addSelected(new Position(2,2));
        board.addSelected(new Position(1,2));
        board.addSelected(new Position(0,2));
        assertTrue(board.isSameRowOrCol());
        board.resetSelected();
        board.addSelected(new Position(2,2));
        assertTrue(board.isSameRowOrCol());
        board.resetSelected();
        assertFalse(board.isSameRowOrCol());
    }

    @Test
    void isSameRow() {
        board.addSelected(new Position(2,1));
        board.addSelected(new Position(2,0));
        board.addSelected(new Position(2,2));
        assertTrue(board.isSameRowOrCol());
    }

    @Test
    void isSameCol() {
        board.addSelected(new Position(2,2));
        board.addSelected(new Position(1,2));
        board.addSelected(new Position(0,2));
        assertTrue(board.isSameRowOrCol());
    }
}