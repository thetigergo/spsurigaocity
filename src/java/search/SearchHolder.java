package search;

/**
 *
 * @author Administrator
 */

@javax.faces.bean.ManagedBean(name = "searchHolder")
@javax.faces.bean.RequestScoped

public class SearchHolder {
    
    private static String mYearTitle, mPageName = "default.xhtml";    
    private static Short mDocument, mKatuig;
    private static Boolean mDisplayAll;

    public static String getTitulo() {
        return mYearTitle;
    }

    public static void setTitulo(String value) {
        mYearTitle = value;
    }

    public static Short getDocument() {
        return mDocument;
    }

    public static void setDocument(Short value) {
        mDocument = value;
    }

    public static Short getKatuig() {
        return mKatuig;
    }

    public static void setKatuig(Short value) {
        mKatuig = value;
    }

    public static Boolean getDisplayAll() {
        return mDisplayAll;
    }

    public static void setDisplayAll(Boolean DisplayAll) {
        mDisplayAll = DisplayAll;
    }

    public static String getPageName() {
        return mPageName;
    }

    public static void setPageName(String value) {
        mPageName = value;
    }
    
    public Short getMenuDoc(){
        return mDocument;
    }
}
