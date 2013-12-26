package consol.users;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="onlineBean")
public class OnlineBean implements java.io.Serializable {
    
    private java.util.List<OnlineList> mUserList = new java.util.ArrayList<OnlineList>();
    private java.util.List<OnlineList> mLogsList = new java.util.ArrayList<OnlineList>();
    
    public void setUserPage(String value) { /*java.awt.event.ActionEvent event){*/ main.Resources.setPageName(value);}

    public java.util.List<OnlineList> getUserList() {return mUserList;}
    public java.util.List<OnlineList> getLogsList() {return mLogsList;}
    
//    public java.util.List<OnlineList> getUserList() {return mUserList;}

    
    public OnlineBean() {
        logUsers();
        logActivity();
    }

    public final void logUsers() {
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT loginid, happend::TIME FROM legis.userslog() ORDER BY happend DESC ");
            mUserList.clear();
            while (rst.next())
                mUserList.add(new OnlineList(rst.getString(1), new java.text.SimpleDateFormat("HH:mm:ss").format(rst.getTime(2))));
            rst.close();


        } catch (Exception ex) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));
        } finally {
            if (jdbc != null) try {
                jdbc.close();
            } catch (Exception ex) {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_FATAL, "FATAL", ex.getMessage()));
            }
        }
    }

    public final void logActivity() {
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery(
                    "SELECT " +
                        "securities.ngalan, " +
                        "CASE logged_actions.actions WHEN 'U' THEN 'UPDATE' WHEN 'D' THEN 'DELETE' WHEN 'I' THEN 'INSERT' WHEN 'O' THEN 'Log-Out' ELSE 'Log-In' END AS actions, " +
                        "logged_actions.action_tstamp::TIME AS events " +
                    "FROM " +
                        "legis.securities JOIN audit.logged_actions " +
                        "ON securities.loginid = logged_actions.user_name " +
                    "WHERE " +
                        "(logged_actions.action_tstamp::DATE = NOW()::DATE) " +
                    "ORDER BY " +
                        "logged_actions.action_tstamp DESC");
            mLogsList.clear();
            while (rst.next())
                mLogsList.add(new OnlineList(rst.getString(1), rst.getString(2) + "~" + new java.text.SimpleDateFormat("hh:mm:ss a").format(rst.getTime(3))));
            rst.close();


        } catch (Exception ex) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));
        } finally {
            if (jdbc != null) try {
                    jdbc.close();
            } catch (Exception ex) {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_FATAL, "FATAL", ex.getMessage()));
            }
        }
    }
}
