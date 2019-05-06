package data.interfaces;

import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface MapperInterface <T> {
    List<T> getAll() throws Exception;
    T getById(int id) throws Exception;
}
