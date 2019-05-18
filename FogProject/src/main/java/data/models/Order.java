package data.models;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class Order {

    private int id;
    private Request request;

    public Order(int id, Request request) {
        this.id = id;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public Request getRequest() {
        return request;
    }
}
