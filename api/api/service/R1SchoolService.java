package api.service;

import java.util.List;
import api.dao.BaseDAO;
import api.dao.R1SchoolDAO;
import api.model.R1School;
import api.common.BasicTransaction;
import sif3.common.exception.PersistenceException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

public class R1SchoolService extends DBService
{
    private R1SchoolDAO r1SchoolDAO = new R1SchoolDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return r1SchoolDAO;
    }

    //getSchools
    public R1School getSchool(String schoolRefID, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	R1School row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = r1SchoolDAO.getSchool(tx, schoolRefID, zone, context);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "(Error: R1SchoolService) Failed to retrieve school for schoolRefID = '"+ schoolRefID, true, false);
    	}
		return row;
    }
    
    public List<R1School> getSchools(SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {
    	List<R1School> list = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	list = r1SchoolDAO.getSchools(tx, zone, context, pagingInfo);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve schools...some error here = '" , true, false);
    	}
		return list;
    }
    

    

}
