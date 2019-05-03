package data.mappers;

import data.exceptions.RequestExceptions;
import data.interfaces.MapperInterface;
import data.models.Request;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen & Martin Frederiksen
 */
public class RequestMapper implements MapperInterface<Request> {

    @Override
    public List<Request> getAll() throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Request getById(int id) throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Request request) throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
