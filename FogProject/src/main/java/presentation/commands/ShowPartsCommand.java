package presentation.commands;

import data.models.CommandTarget;
import data.exceptions.OrderException;
import data.models.Order;
import data.models.Part;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.AdvancedCalculator;
import logic.facades.MaterialFacade;
import logic.facades.OrderFacade;
import logic.facades.RequestFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class ShowPartsCommand implements Command {

    private String target;
    private MaterialFacade mf;
    private RequestFacade rf;
    private OrderFacade of;
    private AdvancedCalculator calc;

    public ShowPartsCommand(String target) {
        this.target = target;

        mf = new MaterialFacade();
        rf = new RequestFacade();
        of = new OrderFacade();
        
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String> params = new HashMap();
        while (paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            params.put(pName, request.getParameter(pName));
        }

        if(request.getParameter("orderId") != null){
            try {
                Order o = of.getSingle(Integer.parseInt(request.getParameter("orderId")));
                calc = new AdvancedCalculator(
                    o.getRequest().getLength() * 10,
                    o.getRequest().getWidth() * 10,
                    (o.getRequest().getShedLength() > 0 && o.getRequest().getShedWidth() > 0),
                    o.getRequest().getShedLength() * 10,
                    o.getRequest().getShedWidth() * 10,
                    o.getRequest().getAngle() > 0);
            } catch (OrderException ex) {
                ex.printStackTrace();
            }
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

        return new CommandTarget(target, "PartList Loaded Successfully");
    }
}
