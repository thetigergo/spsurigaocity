package frontier;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
//@javax.inject.Named
@javax.faces.bean.ManagedBean(name = "authorsBean")
public class AuthorsBean implements java.io.Serializable {

    private java.util.List<AuthorField> aAuthor = new java.util.ArrayList<AuthorField>();

    public AuthorsBean() {
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();

            java.sql.ResultSet rst = jdbc.executeQuery("SELECT photo, humane(lname, fname, mname), message FROM legis.authors");
            while (rst.next())
                aAuthor.add(new AuthorField(rst.getLong(1), rst.getString(2), rst.getString(3)));
            rst.close();

        } catch (Exception ex) {
            //ex.printStackTrace(out);
            System.out.println(ex.getMessage());
        } finally {
            if (jdbc != null) {
                try {
                    jdbc.close();
                } catch (Exception fex) {
                    //fex.printStackTrace(out);
                }
            }
        }
    }

    public java.util.List<AuthorField> getAuthors() {
        return aAuthor;
    }
}
