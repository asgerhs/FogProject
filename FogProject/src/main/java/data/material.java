package data;

/**
 *
 * @author Martin Frederiksen
 */
public class material {
    private String name;
    private int length;
    private int quantity;
    private String unit;
    private String description;

    public material(String name, int length, int quantity, String unit, String description) {
        this.name = name;
        this.length = length;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit(){
        return unit;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "material{" + "name=" + name + ", length=" + length + ", quantity=" + quantity + ", unit=" + unit + ", description=" + description + '}';
    }
    
    
}
