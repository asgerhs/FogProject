package data.interfaces;

import data.exceptions.MapperExceptions;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Martin Frederiksen
 */
public interface MapperInterface <T> {
    List<T> getMaterials() throws MapperExceptions;
    T getMaterialById(int id) throws MapperExceptions;
    TreeMap<Integer, T> getAllByCategory(int id) throws MapperExceptions;
}
