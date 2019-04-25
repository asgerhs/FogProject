package logic;

import data.models.Material;
import data.models.Part;
import data.models.PartList;

/**
 *
 * @author Andreas Vikke
 */
public class BasicPartListPrinter {
    public static void print(PartList list) {
        list.addPart(new Part(new Material("Test", 300, "pakker", "TTTT"), 30));
        list.addPart(new Part(new Material("Test2", 30, "stk", "TTTTT"), 10));
        
        for(Part p : list.getPartList()) {
            System.out.println(p);
        }
    }
    
    public static void main(String[] args) {
        BasicPartListPrinter.print(new PartList());
    }
}
