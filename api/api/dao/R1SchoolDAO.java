package api.dao;

import api.model.R1School;
import api.common.BasicTransaction;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import sif3.common.exception.PersistenceException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

public class R1SchoolDAO extends BaseDAO
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	//Return Single R1School Object
    @SuppressWarnings("unchecked")
    public R1School getSchool(BasicTransaction tx, String schoolRefId, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	 try
         {
             Criteria criteria = tx.getSession().createCriteria(R1School.class)
                .add(Restrictions.eq("schoolRefId", schoolRefId)); //the actual sql type query
               
             
             List<R1School> schools = criteria.list();
             
             // There can only be a maximum of one
             if (schools.isEmpty()) 
             {
            	 logger.debug("No School with schoolRefID = '"+ schoolRefId);
                 return null;
             }
             else // already exists
             {
             	return schools.get(0); //always 1 if looking for a specific record, so index 0 is the first record
             }
         }
         catch (HibernateException e)
         {
             throw new PersistenceException("Unable to retrieve R1School with schoolRefId = '"+ schoolRefId + "'.", e);
         }	
    }
    
    //Return Multiple R1School Objects
    @SuppressWarnings("unchecked")
	public List<R1School> getSchools(BasicTransaction tx, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {        
    	try
        {
            Criteria criteria = tx.getSession().createCriteria(R1School.class);
               //.add(Restrictions.eq("environmentID", environmentID))
               //.add(Restrictions.eq("adapterType", adapterType.name()));
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve List of R1Schools from R1SchoolDAO");
        }	
    }

}
