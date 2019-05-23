package presentation;

import data.ExceptionLogger;
import data.exceptions.CommandException;
import data.models.CommandTarget;
import data.models.LoggerEnum;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            String commandKey = request.getParameter("command");
            Command command = CommandList.commandForm(commandKey);
            
            CommandTarget commandTarget = command.execute(request);
            
            response.setHeader("message", commandTarget.getMessage());
            if(commandTarget.getRedirect())
                response.setHeader("redirect", commandTarget.getTarget());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(commandTarget.getTarget());
            dispatcher.forward(request, response);
        } catch (CommandException | ServletException | IOException ex) {
            response.setHeader("error", "true");
            response.setHeader("message", ex.getMessage());
            
            System.out.println(ex.getMessage());
            ExceptionLogger.log(LoggerEnum.FRONTCONTROLLER, "Error in FrontController Method: \n" + ex.getMessage(), ex.getStackTrace());
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
