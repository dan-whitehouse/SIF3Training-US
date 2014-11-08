package api.model;

import java.io.Serializable;
import java.util.Date;

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
