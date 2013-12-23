/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.ManagedBean(name = "sessionBean")
@javax.faces.bean.RequestScoped
public class SessionBean implements java.io.Serializable {

    private static final long serialVersionUID = 7925647923084091037L;
    private java.util.List<String> urls;

    public void session(java.awt.event.ActionEvent ae) {
        search.SearchHolder.setPageName("save/resolution.xhtml");
    }
}
