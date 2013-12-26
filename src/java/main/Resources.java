package main;

/**
 *
 * @author Administrator
 */
public class Resources {

    private static java.util.Map<String, String> mapUserID = new java.util.HashMap<String, String>();
    private static java.util.Map<String, String> mapUserName = new java.util.HashMap<String, String>();
            
    private static String mCSS1, mRichAJAX, mYearTitle, mPageName = "default.xhtml";
    private static Short  mDocument, mKatuig;
    private static Boolean mDisplayAll;



    public static String getCSS1() {return mCSS1;}
    protected static void setCSS1(String value) {mCSS1 = value;}

    public static String getRichAjax() {return mRichAJAX;}
    protected static void setRichAjax(String value) {mRichAJAX = value;}

    
    
    
    public static String getUserID(String session) {return mapUserID.get(session);}
    protected static void setUserID(String session, String value) {mapUserID.put(session, value);}
//    protected static void setLoginID(String user, String name) {testry.put(user, name);}
//    public static String getLoginID()
    
    public static void removeUserLog(String session) {System.out.println(mapUserID.remove(session));System.out.println(mapUserName.remove(session));}
    
    public static String getUserName(String session) {return mapUserName.get(session);}
    protected static void setUserName(String session, String value) {mapUserName.put(session, value);}

    


    public static String getTitulo() {return mYearTitle;}
    public static void setTitulo(String value) {mYearTitle = value;}

    public static Short getDocument() {return mDocument;}
    public static void setDocument(Short value) {mDocument = value;}

    public static Short getKatuig() {return mKatuig;}
    public static void setKatuig(Short value) {mKatuig = value;}

    public static String getPageName() {return mPageName;}
    public static void setPageName(String value) {mPageName = value;}

//    public static Short getBackCount() {return mBackCount;}
//    public static void setOnCount() {mBackCount--;}
//    public static void setBackCount(Short value) {mBackCount = value;}
    
    public static Boolean getDisplayAll() {return mDisplayAll;}
    public static void setDisplayAll(Boolean value) {mDisplayAll = value;}
}
