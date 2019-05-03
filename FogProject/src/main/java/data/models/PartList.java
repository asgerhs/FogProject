package data.models;

import java.util.ArrayList;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class PartList {
    private ArrayList<Part> partList;

    public PartList() {
        partList = new ArrayList();
    }

    public ArrayList<Part> getPartList() {
        return partList;
    }
    
    
    
    public void addPart(Part part) {
        partList.add(part);
    }
}
