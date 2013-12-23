package search;

/**
 *
 * @author Administrator
 */
public class SearchTableField {

    private String mTitle, mKlass, mType, mAuthor,mTempAuthor,mTempKlass,mTempTitle,mTempType;
    private Short mNumber, mSeries, mTempNo, mTempYear;
    private Long mDocument, mIDseq;

    public SearchTableField(Long idseq, Short series, Short number, String title, String klass, String type, String author, Long document) {
        mNumber = number; mTempNo = number;
        mTitle = title; mTempTitle=title;
        mKlass = klass; mTempKlass=klass;
        mType = type; mTempType=type;
        mAuthor = author; mTempAuthor=author;
        mSeries = series; mTempYear = series;
        mDocument = document;
        mIDseq=idseq;
        
    } 
    

    public Short getNumber() {return mNumber;}
    public void setNumber(Short Number) {mNumber = Number;}

    public Short getSeries() {  return mSeries;}
    public void setSeries(Short Series) {mSeries = Series;} 
    
    public String getTitle() {return mTitle;}    
    public void setTitle(String value) {mTitle = value;}

    public String getKlass() { return mKlass;}
    public void setKlass(String value) {mKlass = value;}

    public String getType() {
        char level = mType.charAt(0);
        switch (level) {
            case 'C':
                mType = "City";
                break;
            case 'B':
                mType = "Barangay";
                break;
        }
        return mType;
    }
    public void setType(String value) {mType = value;}

    public String getAuthor() {return mAuthor;}
    public void setAuthor(String value) {mAuthor = value;}

    public Long getDocument() {return mDocument;}

    public Long getIDseq() {return mIDseq;}
    public void setIDseq(Long IDseq) {mIDseq = IDseq;}

    public Short getTempNo() {return mTempNo;}

    public Short getTempYear() {return mTempYear;}

    public String getTempAuthor() { return mTempAuthor;}

    public String getTempKlass() {return mTempKlass;}

    public String getTempTitle() {return mTempTitle;}

    public String getTempType() {return mTempType;}    
    
        
}
