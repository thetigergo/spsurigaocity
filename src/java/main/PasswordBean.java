package main;

/**
 *
 * @author felix
 */
@javax.faces.bean.ManagedBean(name = "passwordBean")
@javax.faces.bean.RequestScoped
public class PasswordBean implements java.io.Serializable {

    //private javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage();
    private final javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
//    private final String mHome = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/";
//    private final String mServer = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + "/";
    private final String sessionID = request.getRequestedSessionId();

    
    private final boolean NUMERIC = true;

    private String userID = Resources.getUserID(sessionID), newpwd, verified;

//    private final String[] FIELDS = {"add_user", "edit_user", "erase_user", "memo_order", "communication", "endorsement", "service_rec", "minutas", "journal", "ordinances", "resolutions", "mission_vision", "procedures", "power_duties", "officials"};


    public String getUserID() {return userID;}
    //public void setUserID(String value) {userID = value;}

    public String getNewPwd() {return newpwd;}
    public void setNewPwd(String value) {newpwd = value;}

    public String getVerifyPwd() {return verified;}
    public void setVerifyPwd(String value) {verified = value;}

    public String Proceed() {
        String proceed = null;
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();

        if (!verified.equals(newpwd)) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN, "Password mismatch.", ""));
            return proceed;
        }
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();

            database.SQLExecute saver = new database.SQLExecute("legis.securities");
            saver.FieldName("status",  !NUMERIC, enums.Take.UpdateOnly, "N");
            saver.FieldName("passkey",  NUMERIC, enums.Take.UpdateOnly, "MD5('" + verified + "')");
            saver.FieldName("loginid", !NUMERIC, enums.Take.ConditionOnly, userID);
            int success = jdbc.executeUpdate(saver.Perform(enums.Fire.doUpdate));
            if (success != 0) jdbc.executeUpdate("INSERT INTO public.loginlogs VALUES('" + userID + "', NOW(), 'Logging-In')");
            
            org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
            context.addCallbackParam("loggedIn", userID);
            
            if (success == 0)
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", "Password not change."));
            else
                proceed = "consol/adminConsol.jsf";

        } catch (Exception ex) {
            fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));
        } finally {
            if (jdbc != null) try {
                    jdbc.close();
            } catch (Exception ex) {
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_FATAL, "FATAL", ex.getMessage()));
            }
            //if (msg.getDetail() != null) javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);
            return proceed;
        }
    }

//    public void addInfo(javax.faces.event.ActionEvent actionEvent) {  
//        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN,"Sample info message", "PrimeFaces rocks!"));  
//    } 
//    public String getErrorMessage() {return errMessage;}
//    public String getInfoMessage() {return infoMessage;}
//    public Boolean getValidationFailed() {return msg.getDetail() != null;}
}
