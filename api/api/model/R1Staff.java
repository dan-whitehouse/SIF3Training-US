package api.model;

import java.io.Serializable;
import java.util.Date;
import sif3.common.model.EnvironmentKey;

//Define database table objects, get and set them.
public class R1Staff extends EnvironmentKey implements Serializable
{
	private static final long serialVersionUID = 4255199616263324184L;
    
	private String staffRefId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String generationCode;
	private String prefix; 
	private Date birthDate;
	private String sexCode;
	private boolean hispanicLatinoEthnicity;
	private String usCitizenshipStatusCode;		   
		

	public R1Staff() {}
	
	public R1Staff(EnvironmentKey environmenKey)
	{
		super(environmenKey);
	}
	
	//-----------------------
	//staffRefId
	public String getStaffRefId()
    {
    	return this.staffRefId;
    }
	
	public void setStaffRefId(String staffRefId)
    {
    	this.staffRefId = staffRefId;
    }
	
	//-----------------------
	//firstName
	public String getFirstName()
    {
    	return this.firstName;
    }
	
	public void setFirstName(String firstName)
    {
    	this.firstName = firstName;
    }
	
	//-----------------------
	//middleName
	public String getMiddleName()
    {
    	return this.middleName;
    }
	
	public void setMiddleName(String middleName)
    {
    	this.middleName = middleName;
    }
	
	//-----------------------
	//lastName
	public String getLastName()
    {
    	return this.lastName;
    }	
	
	public void setLastName(String lastName)
    {
    	this.lastName = lastName;
    }
	
	//-----------------------
	//generationCode
	public String getGenerationCode()
    {
    	return this.generationCode;
    }
	
	public void setGenerationCode(String generationCode)
    {
    	this.generationCode = generationCode;
    }
	
	//-----------------------
	//prefix
	public String getPrefix()
    {
    	return this.prefix;
    }
	
	public void setPrefix(String prefix)
    {
    	this.prefix = prefix;
    }
	
	//-----------------------
	//birthDate
	public Date getBirthDate()
    {
    	return this.birthDate;
    }
	
	public void setBirthDate(Date birthDate)
    {
    	this.birthDate = birthDate;
    }
	
	//-----------------------
	//sexCode
	public String getSexCode()
    {
    	return this.sexCode;
    }
	
	public void setSexCode(String sexCode)
    {
    	this.sexCode = sexCode;
    }
	
	//-----------------------
	//hispanicLatinoEthnicity
	public boolean getHispanicLatinoEthnicity()
    {
    	return this.hispanicLatinoEthnicity;
    }
	
	public void setHispanicLatinoEthnicity(boolean hispanicLatinoEthnicity)
    {
    	this.hispanicLatinoEthnicity = hispanicLatinoEthnicity;
    }
	
	//-----------------------
	//usCitizenshipStatusCode
	public String getUsCitizenshipStatusCode()
    {
    	return this.usCitizenshipStatusCode;
    }
	
	public void setUsCitizenshipStatusCode(String usCitizenshipStatusCode)
    {
    	this.usCitizenshipStatusCode = usCitizenshipStatusCode;
    }

	//---------------------------------------------------
	
	@Override
	public String toString() 
	{
		return "R1Staff [staffRefId=" + staffRefId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", generationCode=" + generationCode + ", prefix=" + prefix
				+ ", birthDate=" + birthDate + ", sexCode=" + sexCode
				+ ", hispanicLatinoEthnicity=" + hispanicLatinoEthnicity
				+ ", usCitizenshipStatusCode=" + usCitizenshipStatusCode + "]";
	}
}
