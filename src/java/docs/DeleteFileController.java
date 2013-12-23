
package docs;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped//@javax.inject.Named
@javax.faces.bean.ManagedBean(name = "deleteFileController")
public class DeleteFileController {
    
    public void delete(Long mIDseq,Short document) {
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
              
        org.postgresql.core.BaseConnection jdbc = null;
        try {
            jdbc = (org.postgresql.core.BaseConnection) new database.DBPgConn().getLink();
            jdbc.setAutoCommit(false); 
            String sql=null;
            
            switch (document) {
                    case 0:sql="DELETE FROM legis.memoranda WHERE idkeyseq=" + mIDseq ;break;
                    case 1:sql="DELETE FROM legis.communication WHERE idkeyseq=" + mIDseq ;break;
                    case 2:sql="DELETE FROM legis.endorsement WHERE idkeyseq=" + mIDseq ;break;
                    case 3:sql="DELETE FROM legis.ordinance WHERE idkeyseq=" + mIDseq ;break;
                    case 4:sql="DELETE FROM legis.resolution WHERE idkeyseq=" + mIDseq ;break;
                        }
                java.sql.PreparedStatement ps = jdbc.prepareStatement(sql);
                ps.executeUpdate();
                jdbc.commit();                
                context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, "DELETE RECORD", "Successfully deleted!"));
               
        } catch (Exception ex) {
            context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));
        } finally {
            if (jdbc != null) {
                try {
                    jdbc.close();
                } catch (Exception ex) {
                    context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR:", ex.getMessage()));
                }
            }
        }
    }
}
