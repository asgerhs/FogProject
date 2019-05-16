package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.RequestExceptions;
import data.mappers.RequestMapper;
import data.models.Request;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestFacade implements FacadeInterface<Request, Integer>{
    RequestMapper rm = new RequestMapper(new DataSourceMySQL().getDataSource());
    
    @Override
    public List<Request> getAll() throws RequestExceptions {
        return rm.getAll();
    }

    @Override
    public Request getById(Integer id) throws RequestExceptions {
        return rm.getById(id);
    }
    
    public void add(Request request) throws RequestExceptions {
        rm.add(request);
    }
}
