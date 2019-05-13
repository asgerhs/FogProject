package logic.interfaces;

import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface FacadeInterface <T, S>{
    List<T> getAll() throws Exception;
    T getById(S t) throws Exception;
}
