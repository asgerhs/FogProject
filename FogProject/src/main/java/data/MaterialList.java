package data;

import java.util.ArrayList;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class MaterialList {

    private ArrayList<material> stockList;

    public MaterialList() {
        stockList = new ArrayList();
    }

    public ArrayList<material> getStockList() {
        return stockList;
    }

    public void addMaterialToStockList(material mat) {
        stockList.add(mat);
    }

}
