package data.interfaces;

import java.util.List;

/**
 *
 * @author Martin Frederiksen
 * @param <T> Mapper Object
 * @param <S> Parameter Object
 */
public interface MapperInterface <T, S> {
    List<T> getAll() throws Exception;
    T getSingle(S s) throws Exception;
    void add(T t) throws Exception;
}

