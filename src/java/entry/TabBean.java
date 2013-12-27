package entry;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.RequestScoped
@javax.faces.bean.ManagedBean
public class TabBean {

    public void onTabChange(org.primefaces.event.TabChangeEvent event) {
        javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage("Tab Changed", "Active Tab:" + event.getTab().getId());

        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onTabClose(org.primefaces.event.TabCloseEvent event) {
        javax.faces.application.FacesMessage msg = new javax.faces.application.FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());

        javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
