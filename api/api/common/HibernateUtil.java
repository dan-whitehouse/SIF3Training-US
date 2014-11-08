package api.common;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	private static SessionFactory sessionFactory;

	static
	{
		try
		{
		    sessionFactory = new  Configuration().configure("r1.hibernate.cfg.xml").buildSessionFactory();		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static void loadSubObject(Object proxy)
	{
		Hibernate.initialize(proxy);
	}

	public static void shutdown()
	{
		try
		{
			getSessionFactory().close();
		}
		catch (Exception ex) {} // nothing we can really do!
	}
}
