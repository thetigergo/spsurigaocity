package docs;

/**
 *
 * @author felix
 */
@javax.faces.bean.RequestScoped//@javax.inject.Named
@javax.faces.bean.ManagedBean(name = "fileUploadController")
public class FileUploadController implements java.io.Serializable {

    private final int SUKOD = 2048;
    private org.primefaces.model.UploadedFile upFile;
    private String mTitle;
    private char mlevel;
    private int  mDocu, mKlase, mAuthor;
    private short mYear;
    private String[] DBTABLE = {"memoranda", "communication", "endorsement", "ordinance", "resolution"};
    private String[] CAPTION = {"Memo Order", "Communication", "Endorsement", "Ordinance", "Resolution", "Minutes", "Service Record"};

    private String getCaption() {
        return CAPTION[main.Resources.getDocument()];
    }
    
    public FileUploadController(){
        mYear = (short) java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        mTitle = Title;
    }

    public int getKlase() {
        return mKlase;
    }

    public void setKlase(int Klase) {
        mKlase = Klase;
    }

    public int getAuthor() {
        return mAuthor;
    }

    public void setAuthor(int Author) {
        mAuthor = Author;
    }

    public char getLevel() {
        return mlevel;
    }

    public void setLevel(char level) {
        mlevel = level;
    }

    public short getYear() {
        return mYear;
    }

    public void setYear(short Year) {
        mYear = Year;
    }
    
    

    public int getDocu() {
        return mDocu;
    }

    public void setDocu(int Docu) {
        mDocu = Docu;
    }

    public org.primefaces.model.UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(org.primefaces.model.UploadedFile value) {
        upFile = value;
    }


    //public void handleFileUpload(org.primefaces.event.FileUploadEvent event) {
    public void uploadFile() {
        if (upFile == null) {
            return;
        }
        javax.faces.context.FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();

        org.postgresql.core.BaseConnection jdbc = null;
        Short document = main.Resources.getDocument();
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
                switch (document) {
                    case 0:
                    case 1:
                    case 2:
                        sql= "INSERT INTO "
                        + "legis." + DBTABLE[document] +" (series,numbers,titles,document,class,types) "
                        + "VALUES "
                        + "(" + mYear + ","
                        + mDocu + ",'"
                        + mTitle + "',"
                        + oid + ","
                        + mKlase + ",'"
                        + mlevel + "')";
                        break;
                    case 3:
                    case 4:                    
                       sql= "INSERT INTO "
                        + "legis." + DBTABLE[document] +" (series,numbers,titles,document,class,types,author) "
                        + "VALUES "
                        + "(" + mYear + ","
                        + mDocu + ",'"
                        + mTitle + "',"
                        + oid + ","
                        + mKlase + ",'"
                        + mlevel + "',"
                        + mAuthor + ")";
                        break;
                    case 5:
                }
                java.sql.PreparedStatement ps = jdbc.prepareStatement(sql);
                ps.executeUpdate();
                jdbc.commit();
                bis.close();
                context.addMessage(null, new javax.faces.application.FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, getCaption()+ " No. " + mDocu+"-"+mYear+ " was successfully saved!",null));
                
                
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
