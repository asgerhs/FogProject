package data.interfaces;

import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface MapperInterface <T, S> {
    List<T> getAll() throws Exception;
    T getSingle(S t) throws Exception;
}
