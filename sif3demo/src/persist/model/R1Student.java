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
public class R1Student implements Serializable
{
	private static final long serialVersionUID = 4255199616263324174L;
    
	private String studentRefId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String generationCode;
	private String prefix; 
	private Date birthDate;
	private String sexCode;
	private boolean hispanicLatinoEthnicity;
	private String usCitizenshipStatusCode;		   
	private String counselor;
	private String cohortGraduationYear;
		

	public R1Student() {}
		
	//-----------------------
	public String getStudentRefId()
    {
    	return this.studentRefId;
    }
	
	public void setStudentRefId(String studentRefId)
    {
    	this.studentRefId = studentRefId;
    }
	
	//-----------------------
	public String getFirstName()
    {
    	return this.firstName;
    }
	
	public void setFirstName(String firstName)
    {
    	this.firstName = firstName;
    }
	
	//-----------------------
	public String getMiddleName()
    {
    	return this.middleName;
    }
	
	public void setMiddleName(String middleName)
    {
    	this.middleName = middleName;
    }
	
	//-----------------------	
	public String getLastName()
    {
    	return this.lastName;
    }	
	
	public void setLastName(String lastName)
    {
    	this.lastName = lastName;
    }
	
	//-----------------------
	public String getGenerationCode()
    {
    	return this.generationCode;
    }
	
	public void setGenerationCode(String generationCode)
    {
    	this.generationCode = generationCode;
    }
	
	//-----------------------
	public String getPrefix()
    {
    	return this.prefix;
    }
	
	public void setPrefix(String prefix)
    {
    	this.prefix = prefix;
    }
	
	//-----------------------
	public Date getBirthDate()
    {
    	return this.birthDate;
    }
	
	public void setBirthDate(Date birthDate)
    {
    	this.birthDate = birthDate;
    }
	
	//-----------------------
	public String getSexCode()
    {
    	return this.sexCode;
    }
	
	public void setSexCode(String sexCode)
    {
    	this.sexCode = sexCode;
    }
	
	//-----------------------
	public boolean getHispanicLatinoEthnicity()
    {
    	return this.hispanicLatinoEthnicity;
    }
	
	public void setHispanicLatinoEthnicity(boolean hispanicLatinoEthnicity)
    {
    	this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
    }
	
	//-----------------------
	public String getUsCitizenshipStatusCode()
    {
    	return this.usCitizenshipStatusCode;
    }
	
	public void setUsCitizenshipStatusCode(String usCitizenshipStatusCode)
    {
    	this.usCitizenshipStatusCode = usCitizenshipStatusCode;
    }
	
	//-----------------------
	public String getCounselor()
    {
    	return this.counselor;
    }
	
	public void setCounselor(String counselor)
    {
    	this.counselor = counselor;
    }
	
	//-----------------------
	public String getCohortGraduationYear()
    {
    	return this.cohortGraduationYear;
    }
	
	public void setCohortGraduationYear(String cohortGraduationYear)
    {
    	this.cohortGraduationYear = cohortGraduationYear;
    }
	
	//---------------------------------------------------
	
   

	
	@Override
	public String toString() {
		return "R1Student [studentRefId=" + studentRefId + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", generationCode=" + generationCode + ", prefix="
				+ prefix + ", birthDate=" + birthDate + ", sexCode=" + sexCode
				+ ", hispanicLatinoEthnicity=" + hispanicLatinoEthnicity
				+ ", usCitizenshipStatusCode=" + usCitizenshipStatusCode
				+ ", counselor=" + counselor + ", cohortGraduationYear="
				+ cohortGraduationYear + "]";
	}	
}
