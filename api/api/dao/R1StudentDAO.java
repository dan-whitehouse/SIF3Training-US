package api.dao;

import api.model.R1Student;
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

public class R1StudentDAO extends BaseDAO
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	//Return Single R1Student Object
    @SuppressWarnings("unchecked")
    public R1Student getStudent(BasicTransaction tx, String studentRefId, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	 try
         {
             Criteria criteria = tx.getSession().createCriteria(R1Student.class)
                .add(Restrictions.eq("studentRefId", studentRefId)); //the actual sql type query
               
             
             List<R1Student> students = criteria.list();
             
             // There can only be a maximum of one
             if (students.isEmpty()) 
             {
            	 logger.debug("No Student with staffRefID = "+ studentRefId);
                 return null;
             }
             else // already exists
             {
             	return students.get(0); //always 1 if looking for a specific record, so index 0 is the first record
             }
         }
         catch (HibernateException e)
         {
             throw new PersistenceException("Unable to retrieve R1Student with studentRefId = "+ studentRefId, e);
         }	
    }
    
    //Return Multiple R1Student Objects
    @SuppressWarnings("unchecked")
	public List<R1Student> getStudents(BasicTransaction tx, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {        
    	try
        {
            Criteria criteria = tx.getSession().createCriteria(R1Student.class);
               //.add(Restrictions.eq("environmentID", environmentID))
               //.add(Restrictions.eq("adapterType", adapterType.name()));
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve List of R1Students from R1StudentDAO");
        }	
    }

}
