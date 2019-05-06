package presentation.commands;

import data.exceptions.MapperExceptions;
import data.models.Part;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.AdvancedCalculator;

/**
 *
 * @author Martin Frederiksen
 */
public class ShowPartsCommand implements Command {

    private String target;
    private AdvancedCalculator calc;

    public ShowPartsCommand(String target) {
        this.target = target;
        
        calc = new AdvancedCalculator(7800, 6000, false, false);
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        ArrayList<Part> parts = calc.getParts().getPartList();
        session.setAttribute("parts", parts);
        
        return target;
    }
}
