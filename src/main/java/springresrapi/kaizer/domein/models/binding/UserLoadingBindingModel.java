package springresrapi.kaizer.domein.models.binding;

public class UserLoadingBindingModel {

    private String username;

    private String password;

    public UserLoadingBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
