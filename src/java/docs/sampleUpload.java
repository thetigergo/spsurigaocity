/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package docs;

/**
 *
 * @author Administrator
 */
 
@javax.faces.bean.RequestScoped//@javax.inject.Named
@javax.faces.bean.ManagedBean(name = "sampleUpload")
public class sampleUpload implements java.io.Serializable{

    private String mLname,mFname;
    /**
     * Creates a new instance of sampleUpload
     */
    private final int SUKOD = 2048;
    private org.primefaces.model.UploadedFile upFile;
    
    public org.primefaces.model.UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(org.primefaces.model.UploadedFile value) {
        upFile = value;
    }

    public String getLname() {
        return mLname;
    }
    public void setLname(String Lname) {
        mLname = Lname;
    }

    public String getFname() {
        return mFname;
    }
    public void setFname(String Fname) {
        mFname = Fname;
    }
    
    
    public void uplod() {
         if (upFile == null) {
            return;
        }
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();

        org.postgresql.core.BaseConnection jdbc = null;
        Short document = search.SearchHolder.getDocument();
        String sql=null;
        // Do what you want with the file
        try {
            //short index = 0;
            java.io.InputStream stream = upFile.getInputstream();
            java.io.BufferedInputStream bis = new java.io.BufferedInputStream(stream);

            jdbc = (org.postgresql.core.BaseConnection) new database.DBPgConn().getLink();
            jdbc.setAutoCommit(false);
            org.postgresql.largeobject.LargeObjectManager lobj = jdbc.getLargeObjectAPI();
            long oid = lobj.createLO(org.postgresql.largeobject.LargeObjectManager.READWRITE);
            org.postgresql.largeobject.LargeObject obj = lobj.open(oid, org.postgresql.largeobject.LargeObjectManager.WRITE);

            byte buf[] = new byte[SUKOD];
            int len;
            long total = 0;
            while ((len = bis.read(buf, 0, SUKOD)) > 0) {
                obj.write(buf, 0, len);
                total += len;
            }
            obj.close();
            if (total > 0) {
                //mRefKey = String.valueOf(mCounter++);   
         
                        sql= "INSERT INTO "
                        + "legis.authors (lname,fname,photo) "
                        + "VALUES "
                        + "('" + mLname + "','"
                        + mFname + "',"
                        + oid + ")";
                  
                java.sql.PreparedStatement ps = jdbc.prepareStatement(sql);
                ps.executeUpdate();
                jdbc.commit();
                bis.close();
                context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO,"successfully saved!",null));
                
                
            }

        } catch (Exception ex) {
            if (jdbc != null) {
                try {
                    jdbc.rollback();
                } catch (java.sql.SQLException sex) {
                    context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", sex.getMessage()));
                }
            }
            context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", ex.getMessage()));
        } finally {
            if (jdbc != null) {
                try {
                    jdbc.close();
                } catch (Exception fex) {
                    context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, "ERROR", fex.getMessage()));
                }
            }
        }

    }
}
