package data.models;

/**
 *
 * @author Martin Frederiksen
 */
public class User {
    private String email, password, name, address,zipCity, phone;
    private RoleEnum role;

    public User(String email, String password, RoleEnum role, String name, String address, String zipCity, String phone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.zipCity = zipCity;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public RoleEnum getRole() {
        return role;
    }
}
