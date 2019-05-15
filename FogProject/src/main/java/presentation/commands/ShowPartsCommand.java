package presentation.commands;

import data.exceptions.RequestExceptions;
import data.models.Part;
import data.models.Request;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.AdvancedCalculator;
import logic.facades.MaterialFacade;
import logic.facades.RequestFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class ShowPartsCommand implements Command {

    private String target;
    private MaterialFacade mf;
    private RequestFacade rf;
    private AdvancedCalculator calc;

    public ShowPartsCommand(String target) {
        this.target = target;

        mf = new MaterialFacade();
        rf = new RequestFacade();
        //calc = new AdvancedCalculator(7800, 6000, true, 2100, 6000, false);
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String> params = new HashMap();
        while (paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            params.put(pName, request.getParameter(pName));
        }

        /*if (request.getParameter("requestId") != null) {
            try {
                Request r = rf.getById(Integer.parseInt(request.getParameter("requestId")));
                System.out.println(r.getId());
                calc = new AdvancedCalculator(r.getLength(), r.getWidth(), true, r.getShedLength(), r.getShedWidth(), false);
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
            }
        } else*/ if (params.get("submit") != null) {
            calc = new AdvancedCalculator(
                    Integer.parseInt(params.get("length")) * 10,
                    Integer.parseInt(params.get("width")) * 10,
                    Boolean.parseBoolean(params.get("shed")),
                    Integer.parseInt(params.get("shedLength")) * 10,
                    Integer.parseInt(params.get("shedWidth")) * 10,
                    false);
        }

        ArrayList<Part> wood = calc.getParts().getWoodList();
        ArrayList<Part> misc = calc.getParts().getMiscList();

        session.setAttribute("woodParts", wood);
        session.setAttribute("miscParts", misc);
        session.setAttribute("topViewSVG", calc.getTopViewSVG().getTopViewSVG());
        session.setAttribute("sideViewSVG", calc.getTopViewSVG().getSideViewSVG());

        session.setAttribute("length", calc.getLength());
        session.setAttribute("width", calc.getWidth());
        session.setAttribute("shedLength", calc.getShedLength());
        System.out.println(calc.getShedLength());
        session.setAttribute("shedWidth", calc.getShedWidth());
        session.setAttribute("rafters", calc.getRafters());
        session.setAttribute("posts", calc.getPosts());

        return target;
    }
}
