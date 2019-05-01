package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.MapperExceptions;
import data.models.Material;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.MaterialFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestCommand implements Command {

    private String target;
    private MaterialFacade mf;

    public RequestCommand(String target) {
        this.target = target;
        
        mf = new MaterialFacade();
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        try {
            HttpSession session = request.getSession();

            TreeMap<Integer, Material> mats;
            mats = mf.getAllByCategory(10);

            session.setAttribute("mats", mats);

            return target;
        } catch (MapperExceptions ex) {
            ex.printStackTrace();
            throw new CommandExceptions("Test");
        }
    }
}
