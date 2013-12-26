package consol;

/**
 *
 * @author Administrator
 */
@javax.faces.bean.ViewScoped
@javax.faces.bean.ManagedBean(name = "rightBean")
public class RightBean implements java.io.Serializable {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        count++;
    }
}
