package presentation.commands;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Frederiksen
 */
public class CommandList {
    private static CommandList instance = null;
    private final Map<String, Command> commands = new HashMap();
    
    private CommandList(){
        commands.put("showParts", new ShowPartsCommand("WEB-INF/showParts.jsp"));
        commands.put("request", new RequestCommand("WEB-INF/request.jsp"));
        commands.put("addRequest", new AddRequestCommand(""));
        commands.put("requestList", new RequestListCommand("WEB-INF/requestList.jsp"));
        commands.put("login", new LoginCommand("FrontController?command=request"));
        commands.put("logout", new LogoutCommand("WEB-INF/index.jsp"));
        commands.put("orders", new OrderCommand("WEB-INF/order.jsp"));
        commands.put("showUsers", new ShowUsersCommand("WEB-INF/showUsers.jsp"));
        commands.put("updateRole", new UpdateUserCommand("FrontController?command=showUsers"));
        commands.put("register", new RegisterCommand("WEB-INF/register.jsp"));
        commands.put("addUser", new AddUserCommand("FrontController?command=request"));
    }
    
    public static synchronized Command commandForm(String key){
        if(key == null) key = "back";
        if(instance == null) instance = new CommandList();
        return instance.commands.get(key);
    }
}


