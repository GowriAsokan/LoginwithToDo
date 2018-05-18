package jdosample;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
 
@PersistenceCapable
	public class SigninPojo {

	 @Persistent 
		private String email;

	 @Persistent
	private String password;
	
	 @Persistent
	private String username;
// getters and setters	
	
	 public String getEmail()
	 {
	return this.email;
	 }
	 public void setEmail(String email)
	 {
	this.email= email;
	 }

	 public String getPassword()
	{
		return this.password;
	}
    public void setPassword(String password)
    {
    	this.password = password;
   	}
    
    public String getUsername()
    {
    	return this.username;
    }
    public void setUsername(String username)
    {
    	this.username = username;
    }
}

