package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Part {

    private Material material;
    private int qty;
    private String description;

    public Part(Material material, int qty, String description) {
        this.material = material;
        this.qty = qty;
        this.description = description;
    }

    public Material getMaterial() {
        return material;
    }

    public int getQty() {
        return qty;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Part{" + "material=" + material + ", qty=" + qty + ", description=" + description + '}';
    }
}
