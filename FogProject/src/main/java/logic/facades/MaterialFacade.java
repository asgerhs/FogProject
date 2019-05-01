package logic.facades;

import data.exceptions.MapperExceptions;
import data.mappers.MaterialMapper;
import data.models.Material;
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
    public List<Material> getMaterials() throws MapperExceptions {
        return mm.getMaterials();
    }

    @Override
    public Material getMaterialById(int id) throws MapperExceptions {
        return mm.getMaterialById(id);
    }

    @Override
    public TreeMap<Integer, Material> getAllByCategory(int id) throws MapperExceptions {
        return mm.getAllByCategory(id);
    }

    public static void main(String[] args) throws MapperExceptions {
        MaterialFacade mf = new MaterialFacade();
        TreeMap<Integer, Material> mats = new TreeMap();
        mats = mf.getAllByCategory(1);
        System.out.println(mats);
    }
}
