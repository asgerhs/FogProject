package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.models.Order;
import data.models.RoleEnum;
import data.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.OrderFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderCommand implements Command {

    private String target;
    private OrderFacade of;

    public OrderCommand(String target) {
        this.target = target;
        of = new OrderFacade();
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            if (user.getRole().equals(RoleEnum.ADMIN)) {
                List<Order> orders = of.getAll();
                session.setAttribute("orders", orders);
            } else {
                List<Order> orders = of.getAllByUser(user);
                session.setAttribute("orders", orders);
            }
        } catch (OrderException ex) {
            ex.printStackTrace();
            System.out.println("Something was wrong with orders!");
        }

        return target;
    }
}
