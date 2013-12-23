package sample;

/**
 *
 * @author Administrator
 */

@javax.faces.bean.ViewScoped
@javax.faces.bean.ManagedBean(name = "personBean")
public class PersonBean {

	private String mFirstName, mSurName;

	public String getFirstName() {return mFirstName;}
	public void setFirstName(String value) {mFirstName = value;}

	public String getSurName() {return mSurName;}
	public void setSurName(String value) {mSurName = value;}
	
	public void savePerson(javax.faces.event.ActionEvent actionEvent) {
		javax.faces.context.FacesContext.getCurrentInstance().addMessage(null, new javax.faces.application.FacesMessage("Welcome " + mFirstName + " " + mSurName + "!"));
	}
}
