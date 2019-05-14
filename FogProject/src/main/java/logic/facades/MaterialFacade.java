package logic.facades;

import data.exceptions.MapperExceptions;
import data.mappers.MaterialMapper;
import data.models.Material;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class MaterialFacade implements FacadeInterface<Material>{
    
    MaterialMapper mm = new MaterialMapper();
    
    @Override
    public List<Material> getAll() throws MapperExceptions {
        return mm.getAll();
    }

    @Override
    public Material getById(int id) throws MapperExceptions {
        return mm.getById(id);
    }

    public ArrayList<Material> getAllByCategory(int id) throws MapperExceptions {
        return mm.getAllByCategory(id);
    }

    public static void main(String[] args) throws MapperExceptions {
        MaterialFacade mf = new MaterialFacade();
        ArrayList<Material> mats = new ArrayList();
        mats = mf.getAllByCategory(1);
        mats = mf.getAllByCategory(2);
        System.out.println(mats);
    }
}
