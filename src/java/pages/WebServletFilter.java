package pages;

/**
 *
 * @author Administrator
 */
@javax.servlet.annotation.WebFilter(filterName = "WebServletFilter", urlPatterns = {"/*"})
public class WebServletFilter implements javax.servlet.Filter {
    
    private static final boolean debug = false;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private javax.servlet.FilterConfig filterConfig = null;
    
    public WebServletFilter() {
    }    
    
    private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response) throws java.io.IOException, javax.servlet.ServletException {
        if (debug) {
            log("WebServletFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.

        // For example, a filter that implements setParameter() on a request
        // wrapper could set parameters on the request before passing it on
        // to the filter chain.
	/*
         String [] valsOne = {"val1a", "val1b"};
         String [] valsTwo = {"val2a", "val2b", "val2c"};
         request.setParameter("name1", valsOne);
         request.setParameter("nameTwo", valsTwo);
         */

        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }    
    
    private void doAfterProcessing(RequestWrapper request, ResponseWrapper response) throws java.io.IOException, javax.servlet.ServletException {
        if (debug) {
            log("WebServletFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.

        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */

        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<p><strong>This has been appended by an intrusive filter.</strong></p>");
	
         respOut.println("<p>Params (after the filter chain):<br>");
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         respOut.println(buf.toString() + "<br>");
         }
         respOut.println("</p>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
        
        if (debug) {
            log("WebServletFilter:doFilter()");
        }

        // Create wrappers for the request and response objects.
        // Using these, you can extend the capabilities of the
        // request and response, for example, allow setting parameters
        // on the request before sending the request to the rest of the filter chain,
        // or keep track of the cookies that are set on the response.
        //
        // Caveat: some servers do not handle wrappers very well for forward or
        // include requests.
        RequestWrapper wrappedRequest = new RequestWrapper((javax.servlet.http.HttpServletRequest) request);
        ResponseWrapper wrappedResponse = new ResponseWrapper((javax.servlet.http.HttpServletResponse) response);
        
        doBeforeProcessing(wrappedRequest, wrappedResponse);
        
        Throwable problem = null;
        
        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
        }
        
        doAfterProcessing(wrappedRequest, wrappedResponse);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof javax.servlet.ServletException) {
                throw (javax.servlet.ServletException) problem;
            }
            if (problem instanceof java.io.IOException) {
                throw (java.io.IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public javax.servlet.FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(javax.servlet.FilterConfig filter) {
        filterConfig = filter;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("WebServletFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("WebServletFilter()");
        }
        StringBuilder sb = new StringBuilder("WebServletFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
    
    private void sendProcessingError(Throwable thrw, javax.servlet.ServletResponse response) {
        String stackTrace = getStackTrace(thrw);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                java.io.PrintStream ps = new java.io.PrintStream(response.getOutputStream());
                java.io.PrintWriter pw = new java.io.PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                java.io.PrintStream ps = new java.io.PrintStream(response.getOutputStream());
                thrw.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            java.io.StringWriter sw = new java.io.StringWriter();
            java.io.PrintWriter pw = new java.io.PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    /**
     * This request wrapper class extends the support class
     * HttpServletRequestWrapper, which implements all the methods in the
     * HttpServletRequest interface, as delegations to the wrapped request. You
     * only need to override the methods that you need to change. You can get
     * access to the wrapped request using the method getRequest()
     */
    class RequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
        
        public RequestWrapper(javax.servlet.http.HttpServletRequest request) {
            super(request);
        }
        // You might, for example, wish to add a setParameter() method. To do this
        // you must also override the getParameter, getParameterValues, getParameterMap,
        // and getParameterNames methods.
        protected java.util.Hashtable localParams = null;
        
        public void setParameter(String name, String[] values) {
            if (debug) {
                System.out.println("WebServletFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
            }
            
            if (localParams == null) {
                localParams = new java.util.Hashtable();
                // Copy the parameters from the underlying request.
                java.util.Map wrappedParams = getRequest().getParameterMap();
                java.util.Set keySet = wrappedParams.keySet();
                for (java.util.Iterator it = keySet.iterator(); it.hasNext();) {
                    Object key = it.next();
                    Object value = wrappedParams.get(key);
                    localParams.put(key, value);
                }
            }
            localParams.put(name, values);
        }
        
        @Override
        public String getParameter(String name) {
            if (debug) {
                System.out.println("WebServletFilter::getParameter(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameter(name);
            }
            Object val = localParams.get(name);
            if (val instanceof String) {
                return (String) val;
            }
            if (val instanceof String[]) {
                String[] values = (String[]) val;
                return values[0];
            }
            return (val == null ? null : val.toString());
        }
        
        @Override
        public String[] getParameterValues(String name) {
            if (debug) {
                System.out.println("WebServletFilter::getParameterValues(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterValues(name);
            }
            return (String[]) localParams.get(name);
        }
        
        @Override
        public java.util.Enumeration getParameterNames() {
            if (debug) {
                System.out.println("WebServletFilter::getParameterNames() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterNames();
            }
            return localParams.keys();
        }        
        
        @Override
        public java.util.Map getParameterMap() {
            if (debug) {
                System.out.println("WebServletFilter::getParameterMap() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterMap();
            }
            return localParams;
        }
    }

    /**
     * This response wrapper class extends the support class
     * HttpServletResponseWrapper, which implements all the methods in the
     * HttpServletResponse interface, as delegations to the wrapped response.
     * You only need to override the methods that you need to change. You can
     * get access to the wrapped response using the method getResponse()
     */
    class ResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper {
        
        public ResponseWrapper(javax.servlet.http.HttpServletResponse response) {
            super(response);            
        }
        // You might, for example, wish to know what cookies were set on the response
        // as it went throught the filter chain. Since HttpServletRequest doesn't
        // have a get cookies method, we will need to store them locally as they
        // are being set.
	/*
         protected Vector cookies = null;
	
         // Create a new method that doesn't exist in HttpServletResponse
         public Enumeration getCookies() {
         if (cookies == null)
         cookies = new Vector();
         return cookies.elements();
         }
	
         // Override this method from HttpServletResponse to keep track
         // of cookies locally as well as in the wrapped response.
         public void addCookie (Cookie cookie) {
         if (cookies == null)
         cookies = new Vector();
         cookies.add(cookie);
         ((HttpServletResponse)getResponse()).addCookie(cookie);
         }
         */
    }
}
