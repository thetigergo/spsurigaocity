package search;

/**
 *
 * @author Administrator
 */
public class SelectOneMenuField {

    private Integer mID;
    private String mName;

    public SelectOneMenuField(Integer mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public Integer getID() {
        return mID;
    }

    public void setID(Integer mID) {
        this.mID = mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
