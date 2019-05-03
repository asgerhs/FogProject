package logic.interfaces;

import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface FacadeInterface <T>{
    List<T> getAll() throws Exception;
    T getById(int id) throws Exception;
}
