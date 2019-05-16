package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.MapperExceptions;
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
    public List<Material> getAll() throws MapperExceptions {
        return mm.getAll();
    }

    @Override
    public Material getById(String ref) throws MapperExceptions {
        return mm.getById(ref);
    }

    public ArrayList<Material> getAllByCategory(int id) throws MapperExceptions {
        return mm.getAllByCategory(id);
    }
}
