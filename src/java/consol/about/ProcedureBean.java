package consol.about;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name = "procedureBean")
public class ProcedureBean implements java.io.Serializable {

    private String mRuleProc;

    public String getRuleProc() {return mRuleProc;}
    public void setRuleProc(String value) {mRuleProc = value;}

    public ProcedureBean() {
        javax.faces.context.FacesContext fcontext = javax.faces.context.FacesContext.getCurrentInstance();
        org.postgresql.core.BaseStatement jdbc = null;
        try {
            jdbc = database.DataSource.getState();
            java.sql.ResultSet rst = jdbc.executeQuery("SELECT rules_procedure FROM public.setting");
            if (rst.next()) mRuleProc = rst.getString(1);
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
            int success = jdbc.executeUpdate("UPDATE public.setting SET rules_procedure = '" + mRuleProc.replaceAll("'", "''") + "'");

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
