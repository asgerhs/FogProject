package data.interfaces;

import data.models.Request;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen
 */
public interface OrderMapperInterface <T> {

    List<T> getAllOrders() throws Exception;
    T getById(int id) throws Exception;
    void createOrder(Request request) throws Exception;
}
