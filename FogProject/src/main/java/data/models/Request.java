package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Request {
    private int id, width, length, shedWidth, shedLength, angle;
    private String roof, note;
    private User user;

    public Request(int width, int length, int shedWidth, int shedLength, String roof, int angle, String note, User user) {
        this.width = width;
        this.length = length;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roof = roof;
        this.angle = angle;
        this.note = note;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAngle() {
        return angle;
    }

    public String getRoof() {
        return roof;
    }

    public String getNote() {
        return note;
    }

    public User getUser() {
        return user;
    }
}
