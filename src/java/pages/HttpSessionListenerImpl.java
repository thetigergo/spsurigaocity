package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class HttpSessionListenerImpl implements javax.servlet.http.HttpSessionListener/*, javax.servlet.http.HttpSessionAttributeListener*/ {

    private static final java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG, java.text.DateFormat.LONG);
    
    @Override
    public void sessionCreated(javax.servlet.http.HttpSessionEvent se) {
        System.out.println(java.text.MessageFormat.format("Session ID {0} created at {1}", se.getSession().getId(), df.format(new java.util.Date(se.getSession().getCreationTime()))));
 
        // Add a session attribute and add the key to the ServletContext attribute city.sanggunian.SERVLET_CONTEXT_MAP.
        java.util.UUID uuid = java.util.UUID.randomUUID();
        se.getSession().setAttribute(uuid.toString(), uuid);
        java.util.HashMap<String, Object> map = (java.util.HashMap<String, Object>) se.getSession().getServletContext().getAttribute("city.sanggunian.SERVLET_CONTEXT_MAP");
        map.put(se.getSession().getId(), uuid.toString());
    }

    @Override
    public void sessionDestroyed(javax.servlet.http.HttpSessionEvent se) {
        // Remove ServletContext attribute. We don't need to remove our session attribute since the session is being destroyed.
        java.util.HashMap<String, Object> map = (java.util.HashMap<String, Object>) se.getSession().getServletContext().getAttribute("city.sanggunian.SERVLET_CONTEXT_MAP");
        map.remove(se.getSession().getId());
        System.out.println(java.text.MessageFormat.format("Session ID {0} destroyed at {1}", se.getSession().getId(), df.format(new java.util.Date(System.currentTimeMillis()))));
        
        // Remove ServletContext attribute. We don't need to remove our session attribute since the session is being destroyed.
//        main.LoginBean loginBean = (main.LoginBean) se.getSession().getServletContext().getAttribute("loginBean");
//        if (loginBean != null) loginBean.doLogout();
    }

}
