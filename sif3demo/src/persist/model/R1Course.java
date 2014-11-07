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


public class R1Course extends EnvironmentKey implements Serializable
{
	private static final long serialVersionUID = 4255194616261324178L;
    
	private String courseRefId;
	private String schoolRefId;
	private String leaCourseId;
	private String title;
	private String description;
	private String subjectCode;
	private String courseApplicableEducationLevelCode;
	private String scedCourseLevelCode;
	private String scedCourseSubjectAreaCode;
		
	private transient SIFZone defaultZone = null;
	private transient ArrayList<ServiceInfo> services = new ArrayList<ServiceInfo>();

	public R1Course() {}
	
	public R1Course(EnvironmentKey environmenKey)
	{
		super(environmenKey);
	}
	
	//-----------------------
	public String getCourseRefId()
    {
    	return this.courseRefId;
    }
	
	public void setCourseRefId(String courseRefId)
    {
    	this.courseRefId = courseRefId;
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
	public String getLEACourseId()
    {
    	return this.leaCourseId;
    }
	
	public void setLEACourseId(String leaCourseId)
    {
    	this.leaCourseId = leaCourseId;
    }
	
	//-----------------------
	public String geTitle()
    {
    	return this.title;
    }
	
	public void setTitle(String title)
    {
    	this.title = title;
    }
	
	//-----------------------
	public String getDescription()
    {
    	return this.description;
    }
	
	public void setDescription(String description)
    {
    	this.description = description;
    }
	
	//-----------------------
	public String getSubjectCode()
    {
    	return this.subjectCode;
    }
	
	public void setSubjectCode(String subjectCode)
    {
    	this.subjectCode = subjectCode;
    }
	
	//-----------------------
	public String getCourseApplicableEducationLevelCode()
    {
    	return this.courseApplicableEducationLevelCode;
    }
	
	public void setCourseApplicableEducationLevelCode(String courseApplicableEducationLevelCode)
    {
    	this.courseApplicableEducationLevelCode = courseApplicableEducationLevelCode;
    }
	
	//-----------------------
	public String getSCEDCourseLevelCode()
    {
    	return this.scedCourseLevelCode;
    }
	
	public void setSCEDCourseLevelCode(String scedCourseLevelCode)
    {
    	this.scedCourseLevelCode = scedCourseLevelCode;
    }
		
	//-----------------------
	public String getSCEDCourseSubjectAreaCode()
    {
    	return this.scedCourseSubjectAreaCode;
    }
	
	public void setSCEDCourseSubjectAreaCode(String scedCourseSubjectAreaCode)
    {
    	this.scedCourseSubjectAreaCode = scedCourseSubjectAreaCode;
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
		String output = String.format("Course Output [refID: %1$s], [FN: %2$s], [MN: %3$s], [LN: %4$s]", courseRefId, schoolRefId , leaCourseId, title);
	    return output;
    }	
}
