package hu.maven.Components.Ranking;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Result.class)
public interface ResultDAO {

    @SqlUpdate("""
create TABLE if not exists Score(
p1 varchar not null,
p2 varchar not null,
winner varchar not null)
""")
    void create();

    @SqlUpdate("insert into Score values(:p1,:p2,:winner)")
    void addResult(@BindBean Result result);

    @SqlQuery("SELECT * FROM Score")
    List<Result>  listResult();
}
