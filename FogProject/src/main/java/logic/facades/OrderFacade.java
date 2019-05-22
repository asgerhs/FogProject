package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.OrderException;
import data.mappers.OrderMapper;
import data.models.Order;
import data.models.User;
import java.util.List;
import javax.sql.DataSource;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderFacade implements FacadeInterface<Order, Integer> {
    OrderMapper om;
    
    public OrderFacade() {
        om = new OrderMapper(new DataSourceMySQL().getDataSource());
    }
    public OrderFacade(DataSource ds) {
        om = new OrderMapper(ds);
    }

    @Override
    public List<Order> getAll() throws OrderException {
        return om.getAll();
    }

    @Override
    public Order getSingle(Integer id) throws OrderException {
        return om.getSingle(id);
    }

    public void add(Order order) throws OrderException{
        om.add(order);
    }
    
    public List<Order> getAllByUser(User user) throws OrderException {
        return om.getAllByUser(user);
    }
}
