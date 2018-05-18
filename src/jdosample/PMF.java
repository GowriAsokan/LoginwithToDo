package jdosample;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;
public class PMF {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

	private PMF(){
		}
	public static PersistenceManagerFactory get()
			{
	return pmfInstance;
		}
	}

