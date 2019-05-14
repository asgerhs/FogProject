package data.models;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class Category {
    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + '}';
    }
}
