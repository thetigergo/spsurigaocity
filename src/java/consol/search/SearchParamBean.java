package consol.search;

//import search.SearchHolder;

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
        main.Resources.setDocument(mDocument);
        main.Resources.setTitulo(mTitulo);
        main.Resources.setPageName("search/searcher.xhtml");
        main.Resources.setDisplayAll(false);
    }

    public void searchAll(String value,Short menu){
        main.Resources.setDocument(menu);
        main.Resources.setTitulo("");
        main.Resources.setPageName(value);
        main.Resources.setDisplayAll(true);
    }

    public void addNew(java.awt.event.ActionEvent ae) {
        if(main.Resources.getDocument() == 0){
            main.Resources.setPageName("save/memo.xhtml"); 
        }else if(main.Resources.getDocument() == 1){
            main.Resources.setPageName("save/communication.xhtml");        
        }else if(main.Resources.getDocument() == 2){
            main.Resources.setPageName("save/endorsement.xhtml");        
        }else if(main.Resources.getDocument() == 3){
            main.Resources.setPageName("save/ordinance.xhtml");        
        }else if(main.Resources.getDocument() == 4){
            main.Resources.setPageName("save/resolution.xhtml");
        }
    }
   
}
