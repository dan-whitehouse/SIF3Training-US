package api.service;

import org.apache.log4j.Logger;

import sif3.common.exception.PersistenceException;
import api.common.BasicTransaction;
import api.common.HibernateUtil;
import api.dao.BaseDAO;


/**
 * @author Joerg Huber
 *
 */
public abstract class DBService
{
	protected final Logger logger = Logger.getLogger(getClass());

	public abstract BaseDAO getDAO();
	
	public DBService()
	{
	}
	
	public BasicTransaction startTransaction()
	{
		BasicTransaction tx = new BasicTransaction(); 
		tx.startTransaction();
		return tx;
	}
	
	/* Convenience method to 'hide' hibernate as the underlying Data Access Engine */
	public void loadSubObject(Object proxy)
	{
		HibernateUtil.loadSubObject(proxy);
	}
	
	/*
	 * This method takes the given exception and simply re-throws it if it is a IllegalArgumentException, PersistenceException.
	 * Any other exception is mapped to a persistence exception since this service mainly deals with DB operations.
	 * The given errorMsg is added to the IllegalArgumentException or PersistenceException if addErrorMsgToStandardEx is
	 * true. If the exception is any other type then the error message is added regardless addErrorMsgToStandardEx parameter.
	 * The errorMsg is logged if logError is set to true.
	 */
	protected void exceptionMapper(Exception ex, String errorMsg, boolean logError, boolean addErrorMsgToStandardEx) throws IllegalArgumentException, PersistenceException
	{
		if (logError)
		{
			logger.error(errorMsg, ex);
		}
		if (ex instanceof IllegalArgumentException)
		{
			if (addErrorMsgToStandardEx)
			{
				throw new IllegalArgumentException(errorMsg, ex);
			}
			throw (IllegalArgumentException)ex;				
		}
		if 	(ex instanceof PersistenceException)
		{
			if (addErrorMsgToStandardEx)
			{
				throw new PersistenceException(errorMsg, ex);
			}
			throw (PersistenceException)ex;			
		}
		
		// If we get here the ex is of any other type
		throw new PersistenceException(errorMsg, ex);
	}
	
	protected void rollback(BasicTransaction tx)
	{
		if (tx != null)
		{
			tx.rollback();
		}
	}
}
