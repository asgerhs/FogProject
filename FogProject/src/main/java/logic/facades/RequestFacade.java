package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.RequestException;
import data.mappers.RequestMapper;
import data.models.Request;
import java.util.List;
import javax.sql.DataSource;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestFacade implements FacadeInterface<Request, Integer>{
    RequestMapper rm;
    
    public RequestFacade() {
        rm = new RequestMapper(new DataSourceMySQL().getDataSource());
    }
    public RequestFacade(DataSource ds) {
        rm = new RequestMapper(ds);
    }
    
    @Override
    public List<Request> getAll() throws RequestException {
        return rm.getAll();
    }

    @Override
    public Request getSingle(Integer id) throws RequestException {
        return rm.getSingle(id);
    }
    
    public void update(Request rqst) throws RequestException {
        rm.update(rqst);
    }
    
    public void add(Request request) throws RequestException {
        rm.add(request);
    }
    
    public void remove(int id) throws RequestException {
        rm.remove(id);
    }
}
