package edmt.dev.androidgridlayout.Model;

public class Customer {
    String full_name;
    String email;
    String mobile;
    String location;
    String password;
    String api_token;
    String remember_token;
    public  String image;

    public Customer(String full_name, String email, String mobile, String location, String password) {
        this.full_name = full_name;
        this.email = email;
        this.mobile = mobile;
        this.location = location;
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return api_token;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public   String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
