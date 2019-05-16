package data.models;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class Order {

    private int id;
    private int width;
    private int length;
    private int shedWidth;
    private int shedLength;
    private String roof;
    private int angle;
    private String email;

    public Order(int id, String email, int width, int length, int shedWidth, int shedLength, String roof, int angle) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roof = roof;
        this.angle = angle;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public String getRoof() {
        return roof;
    }

    public int getAngle() {
        return angle;
    }
    
    public String getEmail(){
        return email;
    }
}
