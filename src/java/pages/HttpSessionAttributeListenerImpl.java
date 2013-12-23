package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class HttpSessionAttributeListenerImpl implements javax.servlet.http.HttpSessionAttributeListener {

    @Override
    public void attributeAdded(javax.servlet.http.HttpSessionBindingEvent event) {
        System.out.println(java.text.MessageFormat.format("Session ID {0} {1} added {2}", event.getSession().getId(), event.getName(), event.getValue()));
    }

    @Override
    public void attributeRemoved(javax.servlet.http.HttpSessionBindingEvent event) {
        System.out.println(java.text.MessageFormat.format("Session ID {0} {1} added {2}", event.getSession().getId(), event.getName(), event.getValue()));
    }

    @Override
    public void attributeReplaced(javax.servlet.http.HttpSessionBindingEvent event) {
        System.out.println(java.text.MessageFormat.format("Session ID {0} {1} replaced {2}", event.getSession().getId(), event.getName(), event.getValue()));
    }
}
