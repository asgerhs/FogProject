package data.interfaces;

import data.exceptions.MapperExceptions;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Martin Frederiksen
 */
public interface MapperInterface <T> {
    List<T> getAll() throws Exception;
    T getById(int id) throws Exception;
}
