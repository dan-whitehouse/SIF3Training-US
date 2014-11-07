/*
 * SIF3SessionService.java
 * Created: 04/02/2014
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

package persist.service;

import java.util.Date;
import java.util.List;

import persist.dao.BaseDAO;
import persist.dao.R1StaffDAO; //Change to point to persist.dao class
import persist.model.R1Staff; //Change to point to persist.model class
import persist.common.BasicTransaction;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;

/**
 * @author Joerg Huber
 *
 */
public class R1StaffService extends DBService
{
    private R1StaffDAO r1StaffDAO = new R1StaffDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return r1StaffDAO;
    }

    //getStudents
    public R1Staff getStaff(String staffRefId, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	R1Staff row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = r1StaffDAO.getStaff(tx, staffRefId, zone, context);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve staff for refID = '"+ staffRefId, true, false);
    	}
		return row;
    }
    
    public List<R1Staff> getStaffs(SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws IllegalArgumentException, PersistenceException
    {
    	List<R1Staff> list = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	list = r1StaffDAO.getStaffs(tx, zone, context, pagingInfo);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve staffs...some error here = '", true, false);
    	}
		return list;
    }
    

    

}
