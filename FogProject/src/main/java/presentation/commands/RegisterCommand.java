package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.UsersException;
import data.models.CommandTarget;
import data.models.RoleEnum;
import data.models.User;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import logic.facades.UserFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class RegisterCommand implements Command {
    
    private String target;
    private UserFacade uf;
    
    public RegisterCommand(String target) {
        this.target = target;
        uf = new UserFacade();
    }
    
    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String> params = new HashMap();
        while (paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            params.put(pName, request.getParameter(pName));
        }
        if (params.get("name") != null) {
            try {
                User u = new User(
                        params.get("email"),
                        params.get("password"),
                        RoleEnum.CUSTOMER,
                        params.get("name"),
                        params.get("address"),
                        params.get("zipCity"),
                        params.get("phone"));
                uf.add(u);
                uf.validateUser(params.get("email"), params.get("password"));
            } catch (UsersException ex) {
                ex.printStackTrace();
                throw new CommandException("Error user already exists!");
            }       
            CommandTarget ct = new CommandTarget(target, "Success user added");
            ct.setAjaxRedirect();
            return ct;
        }
        return new CommandTarget("WEB-INF/register.jsp", "Successfully loaded site");
    }
}
