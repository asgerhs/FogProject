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
        for(Part p : list.getPartList()) {
            System.out.println(p);
        }
    }
    
    public static void main(String[] args) {
        PartList list = new PartList();
        list.addPart(new Part(new Material("Test", 300, "pakker", "TTTT"), 30));
        list.addPart(new Part(new Material("Test2", 30, "stk", "TTTTT"), 10));
        BasicPartListPrinter.print(list);
    }
}
