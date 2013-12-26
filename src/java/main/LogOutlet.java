package main;

/**
 *
 * @author felix
 */
@javax.servlet.annotation.WebServlet(name = "LogOut", urlPatterns = {"/logOut.html"})
public class LogOutlet extends javax.servlet.http.HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");

        org.postgresql.core.BaseStatement jdbc;
        java.io.PrintWriter out = response.getWriter();
        try {
            jdbc = database.DataSource.getState();
            /*
             * TODO output your page here. You may use following sample code.
             */
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(),
                   sessionID = request.getParameter("sessionid");
            //jdbc.executeUpdate("UPDATE legis.securities SET timeout = NOW(), status = 'F' WHERE (loginid = '" + Resources.getUserID() + "')");
            jdbc.executeUpdate(
                    "INSERT INTO audit.logged_actions(schema_name, table_name, user_name, actions, original_data, new_data)" +
                    "VALUES('legis', 'logged_actions', '" + Resources.getUserID(sessionID) + "', 'O', 'User Intervention', 'Logging-Out')");
            jdbc.executeUpdate("UPDATE legis.securities SET status = 'F' WHERE (loginid = '" + Resources.getUserID(sessionID) + "') AND (status <> 'D')");

            
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv='refresh' content='1;url=" + url + "'/>");
            out.println("<link rel='stylesheet' type='text/css' href='" + Resources.getCSS1() + "'/>");
            out.println("<title>Servlet LogOut</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center'><br/><br/><br/><br/><h1>Signing out . . . . .</h1></div>");
            out.println("</body>");
            out.println("</html>");
            
            
            main.Resources.setDocument(Short.MIN_VALUE);
            main.Resources.setKatuig(Short.MIN_VALUE);
            main.Resources.setPageName("default.xhtml");
            main.Resources.setTitulo("");

            
            request.getSession().invalidate();
            request.logout();

            main.Resources.removeUserLog(sessionID);

        } catch (Exception ex) {
            ex.printStackTrace(out);

        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
