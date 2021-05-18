package hu.maven.Components.Ranking;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * XML-hez tartozó annotáció.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"p1", "p2", "winner"})
/**
 * A {@link Class} tárolja el a játékosokat és a győztes nevét.
 */
public class Result  {
    String p1;
    String p2;
    String winner;

    /**
     * A {@link java.lang.reflect.Constructor} inicializalja a jatekosokat es a gyoztest.
     */
    public Result(){
        p1 ="";
        p2 ="";
        winner ="";
    }

    /**
     * A {@link java.lang.reflect.Constructor} példányosítja a Result()-ban inicializált változókat.
     * @param p1 első játékos
     * @param p2 második játékos
     * @param winner győztes
     */
    public Result(String p1, String p2, String winner){
        this.p1=p1;
        this.p2=p2;
        this.winner=winner;
    }
}