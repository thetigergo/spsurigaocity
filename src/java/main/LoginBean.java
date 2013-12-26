package main;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.SessionScoped
//@javax.inject.Named
@javax.faces.bean.ManagedBean(name="loginBean")
public class LoginBean implements java.io.Serializable {

//    private final javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
//    private final String mHome = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/";
//    private final String mServer = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + "/";
    private String sessionID; // = request.getRequestedSessionId();
    
    private String mUserID, mPassword, mQueryFld;

    private final String[] FIELDS = {"add_user", "edit_user", "erase_user", "memo_order", "communication", "endorsement", "service_rec", "minutas", "journal", "ordinances", "resolutions", "mission_vision", "procedures", "power_duties", "officials", "committee"};
    //private javax.faces.application.FacesMessage messages;

    public LoginBean() {
        StringBuilder mgaField = new StringBuilder();
        for (String field : FIELDS) {
            mGranted.put(field, false);
            mgaField.append(field);mgaField.append(", ");
        }
        mQueryFld = mgaField.deleteCharAt(mgaField.length() - 2).toString();
    }

    public String proceedTo(String value) {
        sessionID = value;
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();

        String retval = null;
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            String passkey = "", ngalan = "";
            jdbc = database.DataSource.getState();

            boolean vacuum = false;
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT dated = NOW()::DATE FROM public.setting");
            if (rst.next()) vacuum = rst.getBoolean(1);
            rst.close();
            
            if (!vacuum) {
                jdbc.executeUpdate("VACUUM FULL VERBOSE ANALYZE;");
                jdbc.executeUpdate("UPDATE public.setting SET dated = NOW()::DATE ");
            }

            rst = jdbc.executeQuery("SELECT " +
                                        "ngalan, " +
                                        "passkey " +
                                    "FROM " +
                                        "legis.securities " +
                                    "WHERE " +
                                       "(loginid = '" + mUserID + "') AND " +
                                       "(userid <> 'D')");
            if (rst.next()) {
                ngalan  = rst.getString(1);
                passkey = rst.getString(2);
            }
            rst.close();
            if (!passkey.isEmpty()) {
                Resources.setUserID(sessionID, mUserID); 
                Resources.setUserName(sessionID, ngalan);
                System.out.println("Login Session: " + sessionID);
                if (org.apache.commons.codec.digest.DigestUtils.md5Hex(mPassword).equals(passkey)) {
                    if (mPassword.toLowerCase().equals("sp"))
                        retval = "needchange";
                    else {
                        int verify = jdbc.executeUpdate("UPDATE legis.securities SET status = 'N' WHERE (loginid = '" + mUserID + "') AND (sessionid = '" + sessionID + "')");
                        if (verify == 0) {
                            jdbc.executeUpdate("INSERT INTO audit.logged_actions(schema_name, table_name, user_name, actions, original_data, new_data)" +
                                    "VALUES('legis', 'logged_actions', '" + mUserID + "', 'T', 'User Logging-In', 'Logging-In')");
                            jdbc.executeUpdate("UPDATE legis.securities SET status = 'N', sessionid = '" + sessionID + "' WHERE (loginid = '" + mUserID + "')");
                        }
                        retval = "consol/adminConsol";
                    }
                } else {
                    fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN, "INVALID PASSWORD", "Re-input valid password"));
                    retval = null;
                }
            } else {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN, "INVALID USER-ID", "Re-input valid user-id"));
                retval = null;
            }
            rst = jdbc.executeQuery("SELECT " + mQueryFld + " FROM legis.securities WHERE (loginid = '" + mUserID + "') AND (passkey = '" + passkey + "')");
            if (rst.next()) {
                for (short abc = 0; abc < FIELDS.length; abc++)
                    mGranted.put(FIELDS[abc], rst.getBoolean(abc + 1));
            }
            rst.close();

            //javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("theUser", mUserID);
            javax.faces.application.ResourceHandler rh = javax.faces.context.FacesContext.getCurrentInstance().getApplication().getResourceHandler();
            Resources.setCSS1(rh.createResource("style.css", "css").getRequestPath());
            Resources.setRichAjax(rh.createResource("richhtmlticker.js", "js").getRequestPath());

        } catch (Exception ex) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));

        } finally {
            if (jdbc != null) try {
                jdbc.close();
            } catch (Exception fex) {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", fex.getMessage()));
            }
        }
        
        main.Resources.setDocument(Short.MIN_VALUE);
        main.Resources.setKatuig(Short.MIN_VALUE);
        main.Resources.setPageName("default.xhtml");
        main.Resources.setTitulo("");

        return retval;
    }

    public void doLogout() {
        if (mUserID == null) return;
        
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            jdbc.executeUpdate(
                    "INSERT INTO audit.logged_actions(schema_name, table_name, user_name, actions, original_data, new_data)" +
                    "VALUES('legis', 'logged_actions', '" + mUserID + "', 'O', 'Session Expired', 'Logging-Out')");
            jdbc.executeUpdate("UPDATE legis.securities SET status = 'F' WHERE (loginid = '" + mUserID + "')");

        } catch (Exception ex) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));

        } finally {
            if (jdbc != null) try {
                jdbc.close();
            } catch (Exception fex) {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", fex.getMessage()));
            }
        }
    }

    

    public String getUserID() {return mUserID;}
    public void setUserID(String value) {mUserID = value;}
    
    public String getUserName() {return Resources.getUserName(sessionID);}

    public String getPassword() {return mPassword;}
    public void setPassword(String value) {mPassword = value;}
    
    
    //<editor-fold defaultstate="collapsed" desc="User Permission">
    private java.util.Map<String, Boolean> mGranted = new java.util.HashMap<String, Boolean>();
    
    //public Boolean getIsMemo() {return true;}
    public Boolean disAllow(String value) {return !mGranted.get(value);}
    //</editor-fold>
}
