package presentation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.facades.MaterialFacade;
import presentation.commands.Command;
import presentation.commands.CommandList;

/**
 *
 * @author Martin Frederiksen
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandKey = request.getParameter("command");
        Command command = CommandList.commandForm(commandKey);

        try {
            String target = command.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(target);
            dispatcher.forward(request, response);
            //should be command exception
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());
            //RequestDispatcher dispatcher = request.getRequestDispatcher(ex.getMessage());
            //dispatcher.forward(request, response);
            //backup exception???
            /*
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("  <head><title>PANIC Page</title></head>");
            out.println("  <body>");
            out.println("    <h3>" + e.getMessage() + "</h3><hr/>");
            out.println("    <pre>");
            //e.printStackTrace(out); // Don't do this in production code!
            out.print("</pre>");
            out.println("  </body>");
            out.println("</html>");
        }
             */
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
