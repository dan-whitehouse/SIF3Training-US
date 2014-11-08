package api.service;

import java.util.List;
import api.dao.BaseDAO;
import api.dao.R1StudentDAO;
import api.model.R1Student;
import api.common.BasicTransaction;
import sif3.common.exception.PersistenceException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

public class R1StudentService extends DBService
{
    private R1StudentDAO r1StudentDAO = new R1StudentDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return r1StudentDAO;
    }

    //getStudents
    public R1Student getStudent(String studentRefID, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	R1Student row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = r1StudentDAO.getStudent(tx, studentRefID, zone, context);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "(Error: R1StudentService) Failed to retrieve student for studentRefID = '"+ studentRefID, true, false);
    	}
		return row;
    }
    
    public List<R1Student> getStudents(SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {
    	List<R1Student> list = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	list = r1StudentDAO.getStudents(tx, zone, context, pagingInfo);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve all students...(R1StudentService)" , true, false);
    	}
		return list;
    }
    

    

}
