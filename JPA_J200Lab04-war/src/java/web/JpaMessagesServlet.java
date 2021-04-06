package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import msg.JpaMasterLocal;


@WebServlet(name = "JpaMessagesServlet", urlPatterns = {"/JpaMessagesServlet"})
public class JpaMessagesServlet extends HttpServlet implements HtmlPrinter{
    
   @EJB
   JpaMasterLocal jpaMasterLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            printHtmlHeader(out);
            out.println("<h1>JpaMessagesServlet</h1>");
            printHtmlEnd(out);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
