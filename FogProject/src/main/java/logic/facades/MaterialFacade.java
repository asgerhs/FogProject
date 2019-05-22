package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.MaterialException;
import data.mappers.MaterialMapper;
import data.models.Material;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class MaterialFacade implements FacadeInterface<Material, String>{
    MaterialMapper mm;
    
    public MaterialFacade() {
        mm = new MaterialMapper(new DataSourceMySQL().getDataSource());
    }
    public MaterialFacade(DataSource ds) {
        mm = new MaterialMapper(ds);
    }
    
    @Override
    public List<Material> getAll() throws MaterialException {
        return mm.getAll();
    }

    @Override
    public Material getSingle(String ref) throws MaterialException {
        return mm.getSingle(ref);
    }

    public ArrayList<Material> getAllByCategory(int id) throws MaterialException {
        return mm.getAllByCategory(id);
    }
}
