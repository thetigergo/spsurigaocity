package database;

/**
 *
 * @author felix
 */
public class DataSource {

    //resource injection
    @javax.annotation.Resource(name = "jdbc/SANGGUNIAN")
    private static javax.sql.DataSource ids;

    //private org.postgresql.core.BaseConnection mJDBC;
    public static org.postgresql.core.BaseConnection getConnect() throws Exception {
        javax.naming.Context context = new javax.naming.InitialContext();
        ids = (javax.sql.DataSource) context.lookup("java:comp/env/jdbc/SANGGUNIAN");
        return (org.postgresql.core.BaseConnection) ids.unwrap(org.postgresql.ds.PGSimpleDataSource.class).getConnection();
    }

    public static org.postgresql.core.BaseStatement getState() throws Exception {
        javax.naming.Context context = new javax.naming.InitialContext();
        ids = (javax.sql.DataSource) context.lookup("java:comp/env/jdbc/SANGGUNIAN");
        return (org.postgresql.core.BaseStatement) ids.unwrap(org.postgresql.ds.PGSimpleDataSource.class).getConnection().createStatement();
    }
}
