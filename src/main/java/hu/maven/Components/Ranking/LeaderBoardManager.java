package hu.maven.Components.Ranking;

import JAXB.JAXBHelper;
import jakarta.xml.bind.JAXBException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A {@link Class} beolvassa az eredményeket és kimenti egy results.xml fájlba.
 */
public class LeaderBoardManager {
    private Leaderboard leaderboard;

    /**
     * A {@link java.lang.reflect.Constructor} betölti a result.xml-t,ha létezik. Amennyiben nem,akkor létrehozza.
     */
    public LeaderBoardManager()  {
        try {
            leaderboard = JAXBHelper.fromXML(Leaderboard.class, new FileInputStream("results.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            leaderboard = new Leaderboard();

            try {
                JAXBHelper.toXML(leaderboard, new FileOutputStream("results.xml"));
            } catch (JAXBException f) {
                e.printStackTrace();
            } catch (FileNotFoundException f) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A {@link java.lang.reflect.Method} az új eredményeket írja bele a results.xml-be.
     * @param result az eredmény
     */
    public void newResult(Result result) {
        leaderboard.getResults().add(result);
        try {
            JAXBHelper.toXML(leaderboard, new FileOutputStream("results.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}