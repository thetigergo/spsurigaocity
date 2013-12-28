package consol.users;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="addUserBean")
public class AddUserBean implements java.io.Serializable {
    
//    private final javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
//    private final String mHome = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + request.getContextPath() + "/";
//    private final String mServer = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + "/";
//    private final String sessionID = request.getRequestedSessionId();

    
    private Boolean mShowDetail = false;
    //private Boolean mAddUser, mEditUser, mEraseUser, mMemoOrder, communication, mEndorsement, mServiceRec, mMinutas, journal, ordinances, mResolutions, mMissionVision, mProcedures, mPowerDuties, mOfficials;
    private String  mLoginID, mUserName;
//    private Short   mCounts = 0;

    private java.util.Map<String, Boolean> mGranted = new java.util.HashMap<String, Boolean>();
    
    private final boolean NUMERIC = true;
    private final String[] FIELDS = {"add_user", "edit_user", "erase_user", "memo_order", "communication", "endorsement", "service_rec", "minutas", "journal", "ordinances", "resolutions", "mission_vision", "procedures", "power_duties", "officials", "committee"};
    

    public String getLoginID() {return mLoginID;}
    public void setLoginID(String value) {mLoginID = value;}

    public String getUserName() {return mUserName;}
    public void setUserName(String value) {mUserName = value;}
        
    public Boolean getAddUser() {return mGranted.get("add_user");}
    public void setAddUser(Boolean value) {mGranted.put("add_user", value);}

    public Boolean getEditUser() {return mGranted.get("edit_user");}
    public void setEditUser(Boolean value) {mGranted.put("edit_user", value);}

    public Boolean getEraseUser() {return mGranted.get("erase_user");}
    public void setEraseUser(Boolean value) {mGranted.put("erase_user", value);}

    public Boolean getMemoOrder() {return mGranted.get("memo_order");}
    public void setMemoOrder(Boolean value) {mGranted.put("memo_order", value);}

    public Boolean getCommunication() {return mGranted.get("communication");}
    public void setCommunication(Boolean value) {mGranted.put("communication", value);}

    public Boolean getEndorsement() {return mGranted.get("endorsement");}
    public void setEndorsement(Boolean value) {mGranted.put("endorsement", value);}

    public Boolean getServiceRec() {return mGranted.get("service_rec");}
    public void setServiceRec(Boolean value) {mGranted.put("service_rec", value);}

    public Boolean getMinutas() {return mGranted.get("minutas");}
    public void setMinutas(Boolean value) {mGranted.put("minutas", value);}

    public Boolean getJournal() {return mGranted.get("journal");}
    public void setJournal(Boolean value) {mGranted.put("journal", value);}

    public Boolean getOrdinances() {return mGranted.get("ordinances");}
    public void setOrdinances(Boolean value) {mGranted.put("ordinances", value);}

    public Boolean getResolutions() {return mGranted.get("resolutions");}
    public void setResolutions(Boolean value) {mGranted.put("resolutions", value);}

    public Boolean getMissionVision() {return mGranted.get("mission_vision");}
    public void setMissionVision(Boolean value) {mGranted.put("mission_vision", value);}

    public Boolean getProcedures() {return mGranted.get("procedures");}
    public void setProcedures(Boolean value) {mGranted.put("procedures", value);}

    public Boolean getPowerDuties() {return mGranted.get("power_duties");}
    public void setPowerDuties(Boolean value) {mGranted.put("power_duties", value);}

    public Boolean getOfficials() {return mGranted.get("officials");}
    public void setOfficials(Boolean value) {mGranted.put("officials", value);}

    public Boolean getShowDetail() {return mShowDetail;}

    

    public AddUserBean() {for (short abc = 0; abc < FIELDS.length; abc++)mGranted.put(FIELDS[abc], false); /*consol.search.SearchHolder.setOnCount();*/}

    public void saveUser(String session) {
        mShowDetail = true;
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();

            database.SQLExecute saver = new database.SQLExecute("legis.securities");
            saver.FieldName("passkey",  NUMERIC, enums.Take.InsertOnly, "MD5('sp')");
            saver.FieldName("ngalan",  !NUMERIC, enums.Take.InsertOnly, mUserName);
            saver.FieldName("loginid", !NUMERIC, enums.Take.InsertOnly, mLoginID);
            saver.FieldName("status",  !NUMERIC, enums.Take.InsertOnly, "F");
            saver.FieldName("userid",  !NUMERIC, enums.Take.InsertOnly, main.Resources.getUserID(session));
            for (String fields : FIELDS) saver.FieldName(fields, NUMERIC, enums.Take.InsertOnly, mGranted.get(fields));
            int success = jdbc.executeUpdate(saver.Perform(enums.Fire.doInsert));

            if (success != 0)
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, "INFO:", "Record saved successfully."));
            else
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN, "WARN:", "Record failed to save."));

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

    public void defPage(Object page) {
        main.Resources.setPageName("default.xhtml");
    }
}
