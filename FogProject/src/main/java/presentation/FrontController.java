package presentation;

import data.exceptions.CommandException;
import data.mappers.MaterialMapper;
import data.models.CommandTarget;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
    

    private static Logger logger = Logger.getLogger(MaterialMapper.class.getName());
     
    public FrontController() {
        try {

            FileHandler handler = new FileHandler("Fog-frontController-log.%u.%g.txt",
                    1024 * 1024, 10);
            logger.addHandler(handler);

            handler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error in logger", new IOException("Error: "));

        }
    }    
    
    
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
            System.out.println(request.getContextPath() + " - " + commandTarget.getTarget());
            if(commandTarget.getRedirect())
                response.setHeader("redirect", commandTarget.getTarget());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(commandTarget.getTarget());
            dispatcher.forward(request, response);
        } catch (CommandException | ServletException | IOException ex) {
            response.setHeader("error", "true");
            response.setHeader("message", ex.getMessage());
            
            System.out.println(ex.getMessage());
            logger.log(Level.SEVERE, "Error: ", ex.getMessage());
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
