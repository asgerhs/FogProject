package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.RequestException;
import data.exceptions.UsersException;
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
    public List<Request> getAll() throws RequestException {
        return rm.getAll();
    }

    @Override
    public Request getById(Integer id) throws RequestException {
        return rm.getById(id);
    }
    
    public void add(Request request) throws RequestException {
        rm.add(request);
    }
    
    public void remove(int id) throws RequestException {
        rm.remove(id);
    }
    
    public static void main(String[] args) throws UsersException, RequestException {
        RequestFacade rf = new RequestFacade();
        UserFacade uf = new UserFacade();
        rf.add(new Request(500, 500, 500, 500, "TEST", 500, "TEST", uf.getById("Alec@somewhere.dk")));
    }
}
