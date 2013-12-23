package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class ServletContextAttributeListenerImpl implements javax.servlet.ServletContextAttributeListener {

    @Override
    public void attributeAdded(javax.servlet.ServletContextAttributeEvent event) {
        System.out.println(java.text.MessageFormat.format("{0} added servlet context attribute {1}", event.getName(), event.getValue()));
    }

    @Override
    public void attributeRemoved(javax.servlet.ServletContextAttributeEvent event) {
        System.out.println(java.text.MessageFormat.format("{0} removed servlet context attribute {1}", event.getName(), event.getValue()));
    }

    @Override
    public void attributeReplaced(javax.servlet.ServletContextAttributeEvent event) {
        System.out.println(java.text.MessageFormat.format("{0} replaced servlet context attribute {1}", event.getName(), event.getValue()));
    }
}
