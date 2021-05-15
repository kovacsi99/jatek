package hu.maven.Components.Model;

public enum Turn {
    /**
     * Első játékos köre.
     */
    PLAYER1,

    /**
     * Második játékos köre.
     */
    PLAYER2 ;

    public Turn otherPlayer(){
        if(this.equals(PLAYER1)){
            return PLAYER2;
        }else{
            return PLAYER1;
        }
    }
}

