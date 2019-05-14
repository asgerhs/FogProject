package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.OrderException;
import data.mappers.OrderMapper;
import data.models.Order;
import data.models.Request;
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
    public Order getById(String t) throws OrderException {
        return om.getById(t);
    }

    public void createOrder(Request req) throws OrderException{
        om.createOrder(req);
    }
}
