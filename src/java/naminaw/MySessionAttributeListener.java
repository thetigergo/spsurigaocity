package naminaw;

/**
 *
 * @author Administrator
 */
public final class MySessionAttributeListener implements javax.servlet.http.HttpSessionAttributeListener {

    @Override
    public void attributeAdded(javax.servlet.http.HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute 
         is added to a session.
         */
        System.out.println("attributeAdded");
    }

    @Override
    public void attributeRemoved(javax.servlet.http.HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute
         is removed from a session.
         */
        System.out.println("attributeRemoved");
    }

    @Override
    public void attributeReplaced(javax.servlet.http.HttpSessionBindingEvent sbe) {
        /* This method is invoked when an attibute
         is replaced in a session.
         */
        System.out.println("attributeReplaced");
    }
}
