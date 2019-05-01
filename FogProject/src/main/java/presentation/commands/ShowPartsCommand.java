package presentation.commands;

import data.models.Part;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.BasicCalculator;

/**
 *
 * @author Martin Frederiksen
 */
public class ShowPartsCommand implements Command {

    private String target;
    private BasicCalculator calc;

    public ShowPartsCommand(String target) {
        this.target = target;
        
        calc = new BasicCalculator();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        ArrayList<Part> parts = calc.getParts().getPartList();
        session.setAttribute("parts", parts);
        
        return target;
    }
}
