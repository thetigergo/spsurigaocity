/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package docs;

/**
 *
 * @author Administrator
 */
public class EditFileController implements java.io.Serializable{

    private boolean isError;
    private String[] DBTABLE = {"memoranda", "communication", "endorsement", "ordinance", "resolution"};
    private String[] CAPTION = {"Memo Order", "Communication", "Endorsement", "Ordinance", "Resolution", "Minutes", "Service Record"};

    private String getCaption() {
        return CAPTION[search.SearchHolder.getDocument()];
    }
   
    public boolean editFileController(Long mIDseq,Short mYear,Short mNumero,String mTitle,Integer mKlass,Character mLevel,Integer mAuthor ) {
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
       
        org.postgresql.core.BaseConnection jdbc = null;
        Short document = search.SearchHolder.getDocument();
        String sql=null;
        try {
            jdbc = (org.postgresql.core.BaseConnection) new database.DBPgConn().getLink();
            jdbc.setAutoCommit(false);    
            
            switch (document) {
                    case 0:
                    case 1:
                    case 2:
                        sql="UPDATE legis." + DBTABLE[document] + " SET "
                        + "series="+  mYear 
                        + ",numbers=" + mNumero
                        + ",titles='"+ mTitle
                        + "',class=" + mKlass
                        + ",types='" + mLevel
                        + "' WHERE idkeyseq=" + mIDseq;
                       break;
                    case 3:
                    case 4:
                        sql="UPDATE legis." + DBTABLE[document] + " SET "
                        + "series="+  mYear 
                        + ",numbers=" + mNumero
                        + ",titles='"+ mTitle
                        + "',class=" + mKlass
                        + ",types='" + mLevel
                        + "',author='" + mAuthor
                        + "' WHERE idkeyseq=" + mIDseq;
                       break;
                    case 5:
                }
                java.sql.PreparedStatement ps = jdbc.prepareStatement(sql);      
                ps.executeUpdate();
                jdbc.commit();                
                context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, "Successfully updated!", getCaption() + " No. " + mNumero +  "-" + mYear));
                isError = false;

        } catch (Exception ex) {              
            isError = true;
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
        
        return isError;
    }
     
}
