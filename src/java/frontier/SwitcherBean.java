package frontier;

import search.SearchHolder;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name = "switcherBean")
public class SwitcherBean implements java.io.Serializable {

    private String mReturnPage = "front/default.xhtml";

//    public void setPage(String value,Short menu) { //java.awt.event.ActionEvent event){        
//        SearchHolder.setDocument(menu);
//        SearchHolder.setTitulo("");
//        SearchHolder.setPageName(value);
//        SearchHolder.setDisplayAll(true);
//    }

    public String getReturnPage() {
        mReturnPage=SearchHolder.getPageName();
        return mReturnPage;
    }
       
    public String getPanelPage() {
        return search.SearchHolder.getPageName();
    }
}
