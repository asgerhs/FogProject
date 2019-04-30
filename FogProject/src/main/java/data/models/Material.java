package data.models;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Martin Frederiksen
 */
public class Material {
    private String ref;
    private String name;
    private int length;
    private int amount;
    private String unit;

    public Material(String ref, String name, int length, int amount, String unit) {
        this.ref = ref;
        this.name = name;
        this.length = length;
        this.amount = amount;
        this.unit = unit;
    }

    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Material{" + "ref=" + ref + ", name=" + name + ", length=" + length + ", amount=" + amount + ", unit=" + unit + '}';
    }
}
