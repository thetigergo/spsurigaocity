package sample;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.ViewScoped
@javax.faces.bean.ManagedBean(name = "pprBean")
public class PPRBean {
    
    private String mFirstName;

    public String getFirstName() {return mFirstName;}

    public void setFirstName(String value) {mFirstName = value;}

}
