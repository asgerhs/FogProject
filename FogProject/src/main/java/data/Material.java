package data;

/**
 *
 * @author Martin Frederiksen
 */
public class Material {
    private String name;
    private int length;
    private String unit;
    private String description;

    public Material(String name, int length, String unit, String description) {
        this.name = name;
        this.length = length;
        this.unit = unit;
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Material{" + "name=" + name + ", length=" + length + ", unit=" + unit + ", description=" + description + '}';
    }
}
