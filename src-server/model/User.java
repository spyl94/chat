package model;

import java.io.Serializable;

import controller.proxy.RemoteClientController;

public class User implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5912873203392339071L;
	private String login;
    private String pass;
    private RemoteClientController stub;
    
    public User() {
        stub = null;
    }
    
    public void setStub(RemoteClientController stub) {
    	this.stub = stub;
    }
    
    public RemoteClientController getStub() {
    	return stub;
    }
    
    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
        
    public String getLogin() {
        return login;
    }
    
    public String getPass() {
        return pass;
    }
    
    public String toString() {
    	return login;
    }
}
