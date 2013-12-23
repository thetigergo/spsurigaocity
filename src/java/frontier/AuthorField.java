package frontier;

/**
 *
 * @author Administrator
 */
public class AuthorField {
    
    private String mMensahe, mNgalan;
    private Long   mOID;

    public AuthorField(Long oid, String names, String message) {mMensahe = message; mOID = oid; mNgalan = names;}
    
    public Long getMyOID() {return mOID;}

    public String getMensahe() {return mMensahe;}

    public String getPangalan() {return mNgalan;}
}
