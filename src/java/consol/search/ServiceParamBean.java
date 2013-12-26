package consol.search;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="serviceParamBean")
public class ServiceParamBean implements java.io.Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Filter Property">
    private String mNgalan;
    private Short  mYear;
    
    public String getNgalan() {return mNgalan;}
    public void setNgalan(String value) {mNgalan = value;}

    public Short getYear() {return mYear;}
    public void setYear(Short value) {mYear = value;}
    //</editor-fold>

    public ServiceParamBean() {mYear = (short)java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);}
    
    public void nowSearch(java.awt.event.ActionEvent ae) {
        main.Resources.setDocument((short)6);
        main.Resources.setTitulo(mNgalan);
        main.Resources.setKatuig(mYear);
        main.Resources.setPageName("search/searcher.xhtml");
    }
}
