package hu.maven.Components.Ranking;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.File;
import java.util.List;

public class jdbiConnection {

    public static void insert(Result result) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:" + System.getProperty("user.home") + File.separator + "Score");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            ResultDAO dao = handle.attach(ResultDAO.class);
            dao.create();
            dao.addResult(result);
        }


    }

    public static List<Result> lister() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:" + System.getProperty("user.home") + File.separator + "Score");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            ResultDAO dao = handle.attach(ResultDAO.class);
            dao.create();
            return dao.listResult();
        }


    }
}
