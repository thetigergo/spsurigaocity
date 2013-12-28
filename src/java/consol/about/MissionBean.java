package consol.about;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name = "missionBean")
public class MissionBean implements java.io.Serializable {

    private final boolean NUMERIC = true;

    private String mMissionVision;

    public String getMissionVision() {return mMissionVision;}
    public void setMissionVision(String value) {mMissionVision = value;}

    public MissionBean() {
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT mission_vision FROM public.setting");
            if (rst.next()) mMissionVision = rst.getString(1);
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
    
    public void postUpdate() {
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            int success = jdbc.executeUpdate("UPDATE public.setting SET mission_vision = '" + mMissionVision.replaceAll("'", "''") + "'");

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
}
