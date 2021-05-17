package hu.maven.Components.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    void init(){
        position = new Position(2,2);
    }

    @Test
    void isNeighbour() {
        assertTrue(position.isNeighbour(new Position(1,2)));
    }

    @Test
    void testToString() {
        assertEquals("(2, 2)",position.toString());
    }

    @Test
    void testEquals() {
        assertFalse(position.equals(new Position(1,2)));
        assertTrue(position.equals(new Position(2,2)));
        assertTrue(position.equals(position));
    }

    @Test
    void row() {
    }

    @Test
    void col() {
    }
}