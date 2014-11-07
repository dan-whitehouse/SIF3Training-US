/*
 * SIF3Session.java
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

package persist.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sif3.common.CommonConstants;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import au.com.systemic.framework.utils.StringUtils;

/**
 * POJO to encapsulate SIF3 Session Information and configuration.
 * 
 * @author Joerg Huber
 *
 */
public class R1School extends EnvironmentKey implements Serializable
{
	private static final long serialVersionUID = 4255199616263324176L;
    
	private String schoolRefId;
	private String leaRefId;
	private String leaName;
	private String streetNumberAndName;
	private String city;
	private String stateCode; 
	private String postalCode;
	private String addressCountyName;
		
	private transient SIFZone defaultZone = null;
	private transient ArrayList<ServiceInfo> services = new ArrayList<ServiceInfo>();

	public R1School() {}
	
	public R1School(EnvironmentKey environmenKey)
	{
		super(environmenKey);
	}
	
	//-----------------------
	public String getSchoolRefId()
    {
    	return this.schoolRefId;
    }
	
	public void setSchoolRefId(String schoolRefId)
    {
    	this.schoolRefId = schoolRefId;
    }
	
	//-----------------------
	public String getLEARefId()
    {
    	return this.leaRefId;
    }
	
	public void setLEARefId(String leaRefId)
    {
    	this.leaRefId = leaRefId;
    }
	
	//-----------------------
	public String getLEAName()
    {
    	return this.leaName;
    }
	
	public void setLEAName(String leaName)
    {
    	this.leaName = leaName;
    }
	
	//streetNumberAndName
	public String getStreetNumberAndName()
    {
    	return this.streetNumberAndName;
    }
	
	public void setStreetNumberAndName(String streetNumberAndName)
    {
    	this.streetNumberAndName = streetNumberAndName;
    }
	
	//city
	public String getCity()
    {
    	return this.city;
    }
	
	public void setCity(String city)
    {
    	this.city = city;
    }
	
	//stateCode
	public String getStateCode()
    {
    	return this.stateCode;
    }
	
	public void setStateCode(String stateCode)
    {
    	this.stateCode = stateCode;
    }
	
	//postalCode
	public String getPostalCode()
    {
    	return this.postalCode;
    }
	
	public void setPostalCode(String postalCode)
    {
    	this.postalCode = postalCode;
    }
	
	
	//addressCountyName
	public String getAddressCountyName()
    {
    	return this.addressCountyName;
    }
	
	public void setAddressCountyName(String addressCountyName)
    {
    	this.addressCountyName = addressCountyName;
    }
	
	
	
	//---------------------------------------------------
	
    public ArrayList<ServiceInfo> getServices()
    {
    	return this.services;
    }

	public void setServices(ArrayList<ServiceInfo> services)
    {
    	this.services = services;
    }

	public SIFZone getDefaultZone()
	{
		return this.defaultZone;
	}

	public void setDefaultZone(SIFZone defaultZone)
	{
		this.defaultZone = defaultZone;
	}


	public boolean hasAccess(AccessRight right, AccessType accessType, String serviceName, SIFZone zone, SIFContext context)
	{
		boolean accessApproved = false;
		for (ServiceInfo serviceInfo : getServices())
		{
			if (serviceInfo.getServiceName().equals(serviceName)) //service name matches
			{
				//Check if Zone matches
				boolean zoneMatches = (zone == null) ? serviceInfo.getZone().getIsDefault() : zone.getId().equals(serviceInfo.getZone().getId());
				
				//check if context matches as well
				boolean contextMatches = (context == null) ? serviceInfo.getContext().getIsDefault() : context.getId().equals(serviceInfo.getContext().getId());

				// Check if access right is the correct level
				if (zoneMatches && contextMatches)
				{
					accessApproved = serviceInfo.getRights().hasRight(right, accessType);
				}
			}
		}
		return accessApproved;
	}


	public List<ServiceInfo> getServiceInfoForService(String serviceName, ServiceType serviceType)
	{
		ArrayList<ServiceInfo> validServices = new ArrayList<ServiceInfo>();
		if (getServices() != null)
		{
			for (ServiceInfo serviceInfo : getServices())
			{
				if (serviceInfo.getServiceName().equals(serviceName) && (serviceInfo.getServiceType() == serviceType))
				{
					validServices.add(serviceInfo);
				}
			}
		}
		return validServices;
	}

	 public List<ServiceInfo> getServiceInfoForService(String serviceName, ServiceType serviceType, AccessRight right, AccessType accessType)
   {
	    ArrayList<ServiceInfo> validServices = new ArrayList<ServiceInfo>();
	    if (getServices() != null)
	    {
	      for (ServiceInfo serviceInfo : getServices())
	      {
	        if (serviceInfo.getServiceName().equals(serviceName) && (serviceInfo.getServiceType() == serviceType))
	        {
	          if (serviceInfo.getRights().hasRight(right, accessType))
	          {
	            validServices.add(serviceInfo);
	          }
	        }
	      }
	    }
	    return validServices;
	}
		

	public ServiceInfo getServiceInfoForService(SIFZone zone, SIFContext context, String serviceName, ServiceType serviceType)
	{
		List<ServiceInfo> validServices = getServiceInfoForService(serviceName, serviceType);
		if ((validServices != null) && (validServices.size() > 0))
		{
			for (ServiceInfo serviceInfo : validServices)
			{
				if (serviceInfo.getZone().getId().equals(zone.getId()) && serviceInfo.getContext().getId().equals(context.getId()))
				{
					return serviceInfo;
				}
			}
		}
		return null; // not found
	}
	
	@Override
    public String toString()
    {
		String output = String.format("School Output [refID: %1$s], [FN: %2$s], [MN: %3$s], [LN: %4$s]", schoolRefId, leaRefId, leaName, streetNumberAndName);
	    return output;
    }	
}
