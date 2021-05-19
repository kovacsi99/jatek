package hu.maven.Components.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {


    Turn turn;
    Turn turn2;
    @BeforeEach
    void init(){
        turn = Turn.PLAYER1;
        turn2 = Turn.PLAYER2;
    }

    @Test
    void otherPlayer() {
    assertNotEquals(Turn.PLAYER1, turn.otherPlayer());
    assertNotEquals(Turn.PLAYER2, turn2.otherPlayer());
    }
}