package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Part {

    private Material material;
    private int qty, price;
    private String description;

    public Part(Material material, int qty, String description, int price) {
        this.material = material;
        this.qty = qty;
        this.description = description;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Part{" + "material=" + material + ", qty=" + qty + ", description=" + description + ", price=" + price + '}';
    }
    
   
}
