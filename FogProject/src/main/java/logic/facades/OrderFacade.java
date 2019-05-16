package logic.facades;

import data.exceptions.OrderException;
import data.exceptions.RequestExceptions;
import data.mappers.OrderMapper;
import data.models.Order;
import data.models.Request;
import data.models.User;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderFacade implements FacadeInterface<Order, Integer> {
    OrderMapper om = new OrderMapper();

    @Override
    public List<Order> getAll() throws OrderException {
        return om.getAll();
    }

    @Override
    public Order getById(Integer id) throws OrderException {
        return om.getById(id);
    }

    public void createOrder(Request req) throws OrderException{
        om.createOrder(req);
    }
    
    public List<Order> getAllByUser(User user) throws OrderException {
        return om.getAllByUser(user);
    }
    
    
    public static void main(String[] args) throws RequestExceptions, OrderException {
        RequestFacade rf = new RequestFacade();
        OrderFacade of = new OrderFacade();
        Request r = rf.getById(12);
        of.createOrder(r);
    }
}
