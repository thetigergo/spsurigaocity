package consol;

/**
 *
 * @author Administrator
 */
public class SearchTableField {
    
    private String  mTitle, mKlass, mType, mAuthor;
    private Short   mNumber, mSeries;
    private Long mDocument;

    public SearchTableField(Short series, Short number, String title, String klass, String type, String author, Long document) {mNumber = number;this.mTitle = title; mKlass = klass; mType = type; mAuthor = author; mSeries = series; mDocument = document;}

    public Short getNumber() {return mNumber;}
//    public void setYear(Short value) {mNumber = value;}

    public String getTitle() {return mTitle;}
//    public void setTitle(String value) {mTitle = value;}

    public String getKlass() {return mKlass;}
//    public void setKlass(String value) {mKlass = value;}

    public String getType() {return mType;}
//    public void setType(String value) {mType = value;}

    public String getAuthor() {return mAuthor;}
//    public void setAuthor(String value) {mAuthor = value;}

    public Short getSeries() {return mSeries;}
//    public void setSeries(Short value) {mSeries = value;}

    public Long getDocument() {return mDocument;}
    
    
}
