import hu.maven.Components.View.BoardGameApplication;
import javafx.application.Application;

/**
 * A {@link Class} a játék elindításáért felelős.
 */
public class App {

    /**
     * A program belépési {@link java.lang.reflect.Method}-a.
     * @param args a program argumentumai.
     */
    public static void main(String[] args) {
        Application.launch(BoardGameApplication.class, args);
    }

}
