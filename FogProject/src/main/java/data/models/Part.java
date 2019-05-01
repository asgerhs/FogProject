package data.models;

import java.util.Objects;

/**
 *
 * @author Andreas Vikke
 */
public class Part {

    private Material material;
    private int qty;

    public Part(Material material, int qty) {
        this.material = material;
        this.qty = qty;
    }

    public Material getMaterial() {
        return material;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "Part{" + "material=" + material + ", qty=" + qty + '}';
    }
}
