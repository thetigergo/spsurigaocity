package docs;

/**
 *
 * @author felix
 */
@javax.servlet.annotation.WebServlet(name = "DocumentViewer", urlPatterns = {"/consol/docViewer.html", "/docViewer.html"})
public class DocumentViewer extends javax.servlet.http.HttpServlet {

    private final int SUKOD = 2048;

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
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/pdf;charset=UTF-8");

        Long oid = Long.parseLong(request.getParameter("docOID"));

        //java.io.PrintWriter out = response.getWriter();
        javax.servlet.ServletOutputStream out = response.getOutputStream();

        org.postgresql.core.BaseConnection jdbc = null;
        try {
            jdbc = (org.postgresql.core.BaseConnection) new database.DBPgConn().getLink();
            jdbc.setAutoCommit(false);
            org.postgresql.largeobject.LargeObjectManager lobj = jdbc.getLargeObjectAPI();
            org.postgresql.largeobject.LargeObject obj = lobj.open(oid, org.postgresql.largeobject.LargeObjectManager.READ);

            byte buf[] = new byte[SUKOD];
            int len;//, tl = 0;
            while ((len = obj.read(buf, 0, SUKOD)) > 0) {
                out.write(buf, 0, len);
            }
            obj.close();
            out.flush();
            jdbc.commit();


        } catch (Exception ex) {
            if (jdbc != null) {
                try {
                    jdbc.rollback();
                } catch (Exception exp) {
                    out.println(exp.getMessage());
                }
            }
            out.println(ex.getMessage());
        } finally {
            if (jdbc != null) {
                try {
                    jdbc.close();
                } catch (Exception exp) {
                    out.println(exp.getMessage());
                }
            }
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
