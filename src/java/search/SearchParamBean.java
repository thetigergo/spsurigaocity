package search;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name = "searchParamBean")
public class SearchParamBean implements java.io.Serializable {

    //<editor-fold defaultstate="collapsed" desc="Filter Property">
    private String mTitulo;
    private Short mDocument;

    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String value) {
        mTitulo = value;
    }

    public String getDocument() {
        return String.valueOf(mDocument);
    }

    public void setDocument(String value) {
        mDocument = Short.parseShort(value);
    }
    //</editor-fold>

    public void nowSearch(java.awt.event.ActionEvent ae) {
        SearchHolder.setDocument(mDocument);
        SearchHolder.setTitulo(mTitulo);
        SearchHolder.setPageName("search/searcher.xhtml");
        SearchHolder.setDisplayAll(false);
    }

    public void searchAll(String value,Short menu){
        SearchHolder.setDocument(menu);
        SearchHolder.setTitulo("");
        SearchHolder.setPageName(value);
        SearchHolder.setDisplayAll(true);
    }

    public void addNew(java.awt.event.ActionEvent ae) {
        if(SearchHolder.getDocument()==0){
            SearchHolder.setPageName("save/memo.xhtml"); 
        }else if(SearchHolder.getDocument()==1){
            SearchHolder.setPageName("save/communication.xhtml");        
        }else if(SearchHolder.getDocument()==2){
            SearchHolder.setPageName("save/endorsement.xhtml");        
        }else if(SearchHolder.getDocument()==3){
            SearchHolder.setPageName("save/ordinance.xhtml");        
        }else if(SearchHolder.getDocument()==4){
            SearchHolder.setPageName("save/resolution.xhtml");
        }
    }
   
}
