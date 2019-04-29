package presentation.commands;

import data.models.Part;
import data.models.PartList;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.BasicCalculator;
import logic.facades.MaterialFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class showParts implements Command {

    private String target;
    private ArrayList<Part> parts;
    private BasicCalculator calc;

    public showParts(String target) {
        this.target = target;
        
        calc = new BasicCalculator();
    }

    @Override
    public String execute(HttpServletRequest request, MaterialFacade mf) {
        HttpSession session = request.getSession();
        //need a get list??
        
        parts = calc.getParts().getPartList();
        session.setAttribute("parts", parts);
        
        return target;
    }
}
