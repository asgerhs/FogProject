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
        
    }
    
    public static synchronized Command commandForm(String key){
        if(key == null) key = "back";
        if(instance == null) instance = new CommandList();
        return instance.commands.get(key);
    }
}
