package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import msg.DbMaster;
import msg.DbMasterLocal;
import msg.JpaMasterLocal;


@WebServlet(name = "Num", urlPatterns = {"/Num"})
public class Num extends HttpServlet {
    
    //@EJB
    //private DbMasterLocal dbMaster;
    
    @EJB
    private JpaMasterLocal jpaMaster;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<Integer> numbers = jpaMaster.getNumbers();
        int sum = jpaMaster.getTotal();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style01.css\"/>");
            out.println("<title>Servlet Test</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"box-1\">");
            String added;
            if(numbers.size() == 0){
                added = "чисел в базе нет.";
            }
            else{
                added = "";
            }
            out.println("<h1><center>Список чисел: <p1>" + added + "</p1></center></h1>");
            for(Integer n : numbers){
                out.println("<ul>");
                out.println("<p1><li><center>" + n + "</center></li></p1>");
                out.println("</ul>");
            }
            out.println("<div>");
            out.println("<hr><h1><center>Сумма чисел в базе: <p1>" + sum + "</p1></center></h1>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
