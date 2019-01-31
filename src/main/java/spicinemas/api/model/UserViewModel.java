package spicinemas.api.model;

public class UserViewModel extends User {
    private String token;

    public UserViewModel(String name,String email,String token){
        super(name,email,"");
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
