package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.MapperExceptions;
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
public class OrderFacade implements FacadeInterface<Order, String> {
    OrderMapper om = new OrderMapper(new DataSourceMySQL().getDataSource());

    @Override
    public List<Order> getAll() throws OrderException {
        return om.getAll();
    }

    @Override
    public Order getById(String username) throws OrderException {
        return om.getById(username);
    }

    public void createOrder(Request req) throws OrderException{
        om.createOrder(req);
    }
    
    public List<Order> getAllByUser(User user) throws OrderException {
        return om.getAllByUser(user);
    }
    
    
    public static void main(String[] args) throws RequestExceptions, OrderException, MapperExceptions {
        RequestFacade rf = new RequestFacade();
        OrderFacade of = new OrderFacade();
        UserFacade uf = new UserFacade();
        
        List<Order> orders = of.getAllByUser(uf.getById("admin"));
        for(Order o : orders){
            System.out.println(o.getId());
        }
        
        //Request r = rf.getById(12);
        //of.createOrder(r);
    }
}
