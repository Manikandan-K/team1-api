package spicinemas.api.model;

public class Users {

    public Users(String name, String email, String encodedPassword) {
        this.name = name;
        this.email = email;
        this.encodedPassword = encodedPassword;
    }



    private int id;
    private  String name;
    private String email;
    private  String encodedPassword;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
