package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Request {
    private int id, width, length, shedWidth, shedLength, angle;
    private String roof, name, address, zipCity, phone, email, note;

    public Request(int width, int length, int shedWidth, int shedLength, String roof, int angle, String name, String address, String zipCity, String phone, String email, String note) {
        this.width = width;
        this.length = length;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roof = roof;
        this.angle = angle;
        this.name = name;
        this.address = address;
        this.zipCity = zipCity;
        this.phone = phone;
        this.email = email;
        this.note = note;
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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCity() {
        return zipCity;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", width=" + width + ", length=" + length + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + ", roof=" + roof + ", angle=" + angle + ", name=" + name + ", address=" + address + ", zipCity=" + zipCity + ", phone=" + phone + ", email=" + email + ", note=" + note + '}';
    }
}
