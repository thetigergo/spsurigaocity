package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class ServletContextListenerImpl implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        System.out.println(java.text.MessageFormat.format("ServletContext initialized {0}", sce.getServletContext().getServerInfo()));
 
        // Add an attribute to the ServletContext.
        sce.getServletContext().setAttribute("city.sanggunian.SERVLET_CONTEXT_MAP", new java.util.HashMap<String, Object>());
 
        // Set the session tracking globally for this servlet context to Cookie. This will override web.xml session tracking.
        java.util.Set<javax.servlet.SessionTrackingMode> modes = java.util.EnumSet.noneOf(javax.servlet.SessionTrackingMode.class);
        modes.add(javax.servlet.SessionTrackingMode.COOKIE);
        sce.getServletContext().setSessionTrackingModes(modes);
    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent sce) {
        // Remove the attribute we set in the ServletContext.
        sce.getServletContext().removeAttribute("city.sanggunian.SERVLET_CONTEXT_MAP");
        System.out.println("ServletContext destroyed.");
    }
}
