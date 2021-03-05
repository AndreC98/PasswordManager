package Object;

import java.io.Serializable;

public class ProgramObject implements Serializable{

    private String website;
    private String password;

    public ProgramObject(String website, String password) {
        this.website = website;
        this.password = password;
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
}
