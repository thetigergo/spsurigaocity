package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class ServletRequestAttributeListenerImpl implements javax.servlet.ServletRequestAttributeListener {

    @Override
    public void attributeAdded(javax.servlet.ServletRequestAttributeEvent srae) {
        System.out.println(java.text.MessageFormat.format("{0} added request attribute {1}", srae.getName(), srae.getValue()));
    }

    @Override
    public void attributeRemoved(javax.servlet.ServletRequestAttributeEvent srae) {
        System.out.println(java.text.MessageFormat.format("{0} removed request attribute {1}", srae.getName(), srae.getValue()));
    }

    @Override
    public void attributeReplaced(javax.servlet.ServletRequestAttributeEvent srae) {
        System.out.println(java.text.MessageFormat.format("{0} replaced request attribute {1}", srae.getName(), srae.getValue()));
    }
}
