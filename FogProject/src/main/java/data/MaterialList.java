package data;

import java.util.ArrayList;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class MaterialList {

    private ArrayList<Material> stockList;

    public MaterialList() {
        stockList = new ArrayList();
    }

    public ArrayList<Material> getStockList() {
        return stockList;
    }

    public void addMaterialToStockList(Material mat) {
        stockList.add(mat);
    }

}
