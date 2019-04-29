package data.interfaces;

import data.exceptions.MapperExceptions;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface MapperInterface <T> {
    List<T> getMaterials() throws MapperExceptions;
    T getMaterialById(int id) throws MapperExceptions;
}
