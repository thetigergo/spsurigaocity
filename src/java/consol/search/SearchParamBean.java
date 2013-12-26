package consol.search;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="searchParamBean")
public class SearchParamBean implements java.io.Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Filter Property">
    private String mTitulo;
    private Short  mDocument;
    
    public String getTitulo() {return mTitulo;}
    public void setTitulo(String value) {mTitulo = value;}

    public String getDocument() {return String.valueOf(mDocument);}
    public void setDocument(String value) {mDocument = Short.parseShort(value);}
    //</editor-fold>
    
    public void nowSearch(java.awt.event.ActionEvent ae) {
        main.Resources.setDocument(mDocument);
        main.Resources.setTitulo(mTitulo);
        main.Resources.setPageName("search/searcher.xhtml");
    }
}
