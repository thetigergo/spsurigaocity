package search;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name = "minutaParamBean")
public class MinutaParamBean implements java.io.Serializable {

    //<editor-fold defaultstate="collapsed" desc="Filter Property">
    private String mTitulo;
    private Short mYear;

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String value) {
        mTitulo = value;
    }

    public Short getYear() {
        return mYear;
    }

    public void setYear(Short value) {
        mYear = value;
    }
    //</editor-fold>

    public MinutaParamBean() {
        mYear = (short) java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    }

    public void nowSearch(java.awt.event.ActionEvent ae) {
        SearchHolder.setDocument((short) 5);
        SearchHolder.setTitulo(mTitulo);
        SearchHolder.setKatuig(mYear);
        SearchHolder.setPageName("search/searcher.xhtml");
    }
}
