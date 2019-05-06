package data.models;

import java.util.ArrayList;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class PartList {
    private ArrayList<Part> wood;
    private ArrayList<Part> misc;

    public PartList() {
        wood = new ArrayList();
        misc = new ArrayList();
    }

    public ArrayList<Part> getWoodList() {
        return wood;
    }
    
    public ArrayList<Part> getMiscList() {
        return misc;
    }
    
    public void addWoodPart(Part part) {
        wood.add(part);
    }
    
    public void addMiscPart(Part part) {
        misc.add(part);
    }
}
