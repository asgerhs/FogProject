package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.MapperException;
import data.mappers.MaterialMapper;
import data.models.Material;
import java.util.ArrayList;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class MaterialFacade implements FacadeInterface<Material, String>{
    MaterialMapper mm = new MaterialMapper(new DataSourceMySQL().getDataSource());
    
    @Override
    public List<Material> getAll() throws MapperException {
        return mm.getAll();
    }

    @Override
    public Material getById(String ref) throws MapperException {
        return mm.getById(ref);
    }

    public ArrayList<Material> getAllByCategory(int id) throws MapperException {
        return mm.getAllByCategory(id);
    }
}
