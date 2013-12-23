package database;

/**
 *
 * @author felix
 */
public class DBPgConn {

    private org.postgresql.core.BaseConnection mJDBC;

    public DBPgConn() throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        mJDBC = (com.mysql.jdbc.Connection) java.sql.DriverManager.getConnection("jdbc:mysql://172.16.0.75:3306/docu_tracer", "root", "admin232009");
        org.postgresql.ds.common.BaseDataSource pg = new org.postgresql.ds.PGSimpleDataSource();
        pg.setServerName("localhost");
        pg.setPortNumber(5432);
        pg.setDatabaseName("sanggunian");
        pg.setUser("postgres");
        pg.setPassword("admindb");
        //pg.setSsl(true);

        mJDBC = (org.postgresql.core.BaseConnection) pg.getConnection();
    }

    public java.sql.ResultSet executeQuery(String sql) throws java.sql.SQLException {
        return mJDBC.createStatement().executeQuery(sql);
    }

    public boolean execute(String sql) throws java.sql.SQLException {
        return mJDBC.createStatement().execute(sql);
    }

    public int executeUpdate(String sql) throws java.sql.SQLException {
        return mJDBC.createStatement().executeUpdate(sql);
    }

    public void close() throws java.sql.SQLException {
        mJDBC.close();
    }

    public void setAutoCommit(boolean autoCommit) throws java.sql.SQLException {
        mJDBC.setAutoCommit(autoCommit);
    }

    public void commit() throws java.sql.SQLException {
        mJDBC.commit();
    }

    public void rollback() throws java.sql.SQLException {
        mJDBC.rollback();
    }

    public org.postgresql.core.BaseConnection getLink() {
        return mJDBC;
    }
}
