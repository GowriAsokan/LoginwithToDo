package jdosample;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
 @PersistenceCapable
 public class LoginPojo {

	 @Persistent 
		private String email;
	 @Persistent
	    private String password;
	 
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
	this.password=password;
	  }
}
