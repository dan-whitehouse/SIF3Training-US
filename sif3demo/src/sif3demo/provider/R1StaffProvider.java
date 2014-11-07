/*
 * StudentProvider.java
 * Created: 30/09/2014
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

package sif3demo.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import sif.dd.us32.conversion.DataModelMarshalFactory;
import sif.dd.us32.conversion.DataModelUnmarshalFactory;
import sif.dd.us32.model.K12StaffCollectionType; //Change to correct SIF object
import sif.dd.us32.model.K12StaffType; //Change to correct SIF object
import sif.dd.us32.model.ObjectFactory;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.infra.rest.provider.BaseProvider;
import sif3demo.ModelObjectConstants;
import persist.converter.R1SIFMapper; //Change to perisist.converter file
import persist.model.R1Staff; //Change to persist.model file
import persist.service.R1StaffService; //Change to persist.service file


public class R1StaffProvider extends BaseProvider
{
	private ObjectFactory oFac = new ObjectFactory();
	private R1StaffService staffService = new R1StaffService(); //?? Is this from the service folder
	private R1SIFMapper mapper = new R1SIFMapper();
	private static DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private static DataModelMarshalFactory marshaller = new DataModelMarshalFactory();
	
	@Override
	public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, IllegalArgumentException
	{
		Collection<R1Staff> r1Staffs = staffService.getStaffs(zone, context, pagingInfo);
		Collection<K12StaffType> sifStaffs = new ArrayList<K12StaffType>();
		
		for(R1Staff r1Staff : r1Staffs)
		{
			sifStaffs.add(mapper.modelToSIF(r1Staff));
		}
		
		K12StaffCollectionType sifStaffCollection = oFac.createK12StaffCollectionType();
		sifStaffCollection.getK12Staff().addAll(sifStaffs);
		
		return sifStaffCollection;
	}
	
	@Override
	public Object retrievByPrimaryKey(String resourceID, SIFZone zone, SIFContext context,RequestMetadata metadata) throws PersistenceException, IllegalArgumentException
	{
		R1Staff r1Staff = staffService.getStaff(resourceID, zone, context);
		
		if(r1Staff == null)
		{
			return null;
		}
		
		K12StaffType sifStaff = mapper.modelToSIF(r1Staff);
		return sifStaff;
	}

	@Override
	public MarshalFactory getMarshaller()
	{
		return marshaller;
	}
	
	@Override
	public UnmarshalFactory getUnmarshaller()
	{
		return unmarshaller;
	}

	@Override
	public ModelObjectInfo getSingleObjectClassInfo()
	{
		return ModelObjectConstants.STAFF;
	}
	
	@Override
	public ModelObjectInfo getMultiObjectClassInfo()
	{
		return ModelObjectConstants.STAFFS;
	}


	//Not Implemented Yet
	@Override
	public List<CreateOperationStatus> createMany(Object arg0, boolean arg1,
			SIFZone arg2, SIFContext arg3, RequestMetadata arg4)
			throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSingle(Object arg0, boolean arg1, SIFZone arg2,
			SIFContext arg3, RequestMetadata arg4)
			throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperationStatus> deleteMany(List<String> arg0, SIFZone arg1,
			SIFContext arg2, RequestMetadata arg3)
			throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSingle(String arg0, SIFZone arg1, SIFContext arg2,
			RequestMetadata arg3) throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OperationStatus> updateMany(Object arg0, SIFZone arg1,
			SIFContext arg2, RequestMetadata arg3)
			throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSingle(Object arg0, String arg1, SIFZone arg2,
			SIFContext arg3, RequestMetadata arg4)
			throws IllegalArgumentException,
			sif3.common.exception.PersistenceException
	{
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public void shutdown()
	{
		// TODO Auto-generated method stub
		
	}
}
