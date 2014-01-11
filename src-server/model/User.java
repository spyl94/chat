package model;

import java.io.Serializable;

public class User implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5912873203392339071L;
	private String login;
    private String pass;
    
    public User() {
        
    }
    
    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
    
    public User(String login, String pass, int sid, int tid) {
        this.login = login;
        this.pass = pass;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPass() {
        return pass;
    }
}
