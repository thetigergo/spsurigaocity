package pages;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean(name="switcherBean")
public class SwitcherBean implements java.io.Serializable {

    private String mReturnPage = "default.xhtml";

    public void setPage(String value) {/*java.awt.event.ActionEvent event){*/mReturnPage = value;}

    public String getReturnPage() {return mReturnPage;}

    public String getPanelPage() {
        //if (consol.search.SearchHolder.getPageName().equals("default.xhtml")) consol.search.SearchHolder.setBackCount((short)0);
        return main.Resources.getPageName();
    }
}
