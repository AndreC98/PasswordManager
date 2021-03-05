package Object;

import java.io.Serializable;

public class ProgramObject implements Serializable{
    private static final long serialVersionUID = 4172304297720653145L;
    private String website;
    private String password;
    private String username;

    public ProgramObject(String website, String password, String username) {
        this.website = website;
        this.password = password;
        this.username = username;
    }
    
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String toString() {
        return "Website: " + this.website + "Username: " + this.username + 
            "password: " + this.password;
    }
}
