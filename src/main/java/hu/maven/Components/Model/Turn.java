package hu.maven.Components.Model;
/**
 *Az osztály meghatározza, hogy melyik játékos köre zajlik.
 */
public enum Turn {
    /**
     * Első játékos köre.
     */
    PLAYER1,

    /**
     * Második játékos köre.
     */
    PLAYER2 ;

    /**
     * Játékosváltásért felelő metódus.
     * @return  a {@code Turn}
     */
    public Turn otherPlayer(){
        if(this.equals(PLAYER1)){
            return PLAYER2;
        }else{
            return PLAYER1;
        }
    }

}

