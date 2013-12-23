package pages;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebListener()
public class ServletRequestListenerImpl implements javax.servlet.ServletRequestListener {

    @Override
    public void requestDestroyed(javax.servlet.ServletRequestEvent sre) {
        if (sre.getServletRequest() instanceof javax.servlet.http.HttpServletRequest) {
            javax.servlet.http.HttpServletRequest hsr = (javax.servlet.http.HttpServletRequest) sre.getServletRequest();
            javax.servlet.http.HttpSession session = hsr.getSession(false);
             
            // We will remove the old UUID and set a new UUID
            if (session != null) {
                java.util.HashMap<String, Object> map = (java.util.HashMap<String, Object>) session.getServletContext().getAttribute("city.sanggunian.SERVLET_CONTEXT_MAP");
                String uuid_key = (String) map.get(session.getId());
                session.removeAttribute(uuid_key);
                java.util.UUID uuid = java.util.UUID.randomUUID();
                session.setAttribute(uuid.toString(), uuid);
                map.put(session.getId(), uuid.toString());
                hsr.removeAttribute("city.sanggunian.UUID_TOKEN");
            }
        }
        System.out.println("Servlet Request destroyed.");
    }

    @Override
    public void requestInitialized(javax.servlet.ServletRequestEvent sre) {
        System.out.println("Servlet Request initialized.");
        if (sre.getServletRequest() instanceof javax.servlet.http.HttpServletRequest) {
            javax.servlet.http.HttpServletRequest hsr = (javax.servlet.http.HttpServletRequest) sre.getServletRequest();
            javax.servlet.http.Cookie[] cookies = hsr.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (javax.servlet.http.Cookie cookie : cookies) {
                    System.out.println(java.text.MessageFormat.format("Cookie --> {0} value --> {1} domain --> {2} max-age --> {3}", cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getMaxAge()));
                }
            }
 
            // We want to set a unique UUID for each request.
            javax.servlet.http.HttpSession session = hsr.getSession(false);
            if (session != null) {
                java.util.HashMap<String, Object> map = (java.util.HashMap<String, Object>) session.getServletContext().getAttribute("city.sanggunian.SERVLET_CONTEXT_MAP");
                String uuid_key = (String) map.get(session.getId());
                java.util.UUID uuid = (java.util.UUID) session.getAttribute(uuid_key);
                hsr.setAttribute("city.sanggunian.UUID_TOKEN", uuid.toString());
            }
 
        }
    }
}
