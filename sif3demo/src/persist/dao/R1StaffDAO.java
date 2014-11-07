/*
 * SIF3SessionDAO.java
 * Created: 30/01/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package persist.dao;

import persist.model.R1Staff; //Change to point to persist.model class
import persist.common.BasicTransaction;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3Queue;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This class provides some low level DB access to SIF3 session information.
 *  
 * @author Joerg Huber
 *
 */
public class R1StaffDAO extends BaseDAO
{
	protected final Logger logger = Logger.getLogger(getClass());
		
    @SuppressWarnings("unchecked")
    
    //Return single staff
    public R1Staff getStaff(BasicTransaction tx, String staffRefId, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(staffRefId))
    	{
    		throw new IllegalArgumentException("staffRefId empty or null.");          
    	}

    	try
    	{
    		Criteria criteria = tx.getSession().createCriteria(R1Staff.class)
    				.add(Restrictions.eq("staffRefId", staffRefId)); //the actual sql type query


    		List<R1Staff> staffs = criteria.list();

    		// There can only be a maximum of one
    		if (staffs.isEmpty()) // no queue for given id
    		{
    			//logger.debug("No Queue for queueID = '"+ queueID + " and adapterType = '" + adapterType + "' exists.");
    			return null;
    		}
    		else // already exists
    		{
    			return staffs.get(0); //always 1 if looking for a specific record, so index 0 is the first record
    		}
    	}
    	catch (HibernateException e)
    	{
    		throw new PersistenceException("Unable to retrieve Staff for staffRefId = '"+ staffRefId + "'.", e);
    	}	

    }
    
    //Return all staff
    public List<R1Staff> getStaffs(BasicTransaction tx, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {   
    	try
        {
            Criteria criteria = tx.getSession().createCriteria(R1Staff.class);
               //.add(Restrictions.eq("environmentID", environmentID))
               //.add(Restrictions.eq("adapterType", adapterType.name()));
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Staff for Staffsssss =");
        }	
    }
}
