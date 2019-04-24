package data;

import java.util.ArrayList;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class MaterialList {

    private ArrayList<material> stockList;

    public MaterialList() {
        stockList = new ArrayList<material>();
    }

    public ArrayList<material> getStockList() {
        return stockList;
    }

    public void addMaterialToStockList(material arg) { //arg værende en buffer for fremtidigt navn
        stockList.add(arg);
    }

}
