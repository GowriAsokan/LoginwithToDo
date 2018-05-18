package jdosample;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ListPojo {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Persistent
	private String list;
	
	@Persistent
	private String CustomerEmail;
	

	public String getList() {
		return this.list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getCustomerEmail() {
		return this.CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.CustomerEmail = customerEmail;
	}
}





















