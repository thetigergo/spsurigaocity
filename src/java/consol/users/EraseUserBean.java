package consol.users;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="eraseUserBean")
public class EraseUserBean implements java.io.Serializable {
    
    private Boolean mShowDetail = false;
    private String  mUserID, mUserName;

    private java.util.Map<String, Boolean> mGranted = new java.util.HashMap<String, Boolean>();
    private java.util.List<Object> mUserList = new java.util.ArrayList<Object>();
    
    private final boolean NUMERIC = true;
    private final String[] FIELDS = {"add_user", "edit_user", "erase_user", "memo_order", "communication", "endorsement", "service_rec", "minutas", "journal", "ordinances", "resolutions", "mission_vision", "procedures", "power_duties", "officials", "committee"},
                           TABLES = {"legis.authors", "legis.classify", "legis.communication", "legis.endorsement", "legis.memoranda", "legis.minutas", "legis.ordinance", "legis.resolution", "legis.securities", "legis.serbisyo"};
    

    public String getUserID() {return mUserID;}
    public void setUserID(String value) {mUserID = value;}

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

    public Boolean getCommittee() {return mGranted.get("committee");}
    public void setCommittee(Boolean value) {mGranted.put("committee", value);}


    
    
    public Boolean getShowDetail() {return mShowDetail;}
    public java.util.List<Object> getUserList() {return mUserList;}



    public EraseUserBean() {
        for (short abc = 0; abc < FIELDS.length; abc++) mGranted.put(FIELDS[abc], false); /*consol.search.SearchHolder.setOnCount();*/

        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT loginid, ngalan FROM legis.securities WHERE (status <> 'D')");
            while (rst.next()) {
                javax.faces.model.SelectItem option = new javax.faces.model.SelectItem(rst.getString(1), rst.getString(2));
                mUserList.add(option);
            }
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

    public void userRetrieve() {
        if(mUserID == null)
            return;
        else if (mUserID.equals(""))
            return;

        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();

        StringBuilder mgaField = new StringBuilder();
        for (short abc = 0; abc < FIELDS.length; abc++) {
            mGranted.put(FIELDS[abc], false);
            mgaField.append(FIELDS[abc]);mgaField.append(", ");
        }
        mgaField = mgaField.deleteCharAt(mgaField.length() - 2);
        
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT " + mgaField + " FROM legis.securities WHERE (loginid = '" + mUserID + "')");
            if (rst.next()) {
                for (short abc = 0; abc < FIELDS.length; abc++)
                    mGranted.put(FIELDS[abc], rst.getBoolean(abc + 1));
            }
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
    
    public void eraseUser() {
        mShowDetail = true; int naihap = 0;
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            for (String lamisa : TABLES) {
                java.sql.ResultSet rst = jdbc.executeQuery("SELECT COUNT(*) FROM " + lamisa + " WHERE (userid = '" + mUserID + "')");
                rst.next(); naihap += rst.getShort(1);
                rst.close();
            }
            if (naihap > 0)
                naihap = jdbc.executeUpdate("UPDATE legis.securities SET status = 'D' WHERE (loginid = '" + mUserID + "')");
            else
                naihap = jdbc.executeUpdate("DELETE FROM legis.securities WHERE (loginid = '" + mUserID + "')");
            
            if (naihap > 0)
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, "INFO:", "User remove successfully."));
            else
                fcontext.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_WARN, "WARNING:", "Failed to remove user"));

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
