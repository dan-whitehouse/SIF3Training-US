package persist.converter;

import sif.dd.us32.model.K12SchoolType;
import sif.dd.us32.model.ObjectFactory;
import sif.dd.us32.model.K12StudentType;
import sif.dd.us32.model.K12StaffType;
import persist.model.R1School;
import persist.model.R1Student;
import persist.model.R1Staff;


public class R1SIFMapper
{
	private ObjectFactory oFac = new ObjectFactory();
	
	
	public K12StudentType modelToSIF(R1Student r1Student)
	{
		K12StudentType sifStudent = oFac.createK12StudentType();
		sifStudent.setRefId(r1Student.getStudentRefId());
		
		return sifStudent;
	}
	
	public R1Student sifToModel(K12StudentType sifStudent)
	{
		R1Student r1Student = new R1Student();
		r1Student.setStudentRefId(sifStudent.getRefId());
		return r1Student;
	}
	
	public K12SchoolType modelToSIF(R1School r1School)
	{
		K12SchoolType sifSchool = oFac.createK12SchoolType();
		sifSchool.setRefId(r1School.getSchoolRefId());
		
		return sifSchool;
	}
	
	public R1School sifToModel(K12SchoolType sifSchool)
	{
		R1School r1School = new R1School();
		r1School.setSchoolRefId(sifSchool.getRefId());
		return r1School;
	}
	
	public K12StaffType modelToSIF(R1Staff r1Staff)
	{
		K12StaffType sifStaff = oFac.createK12StaffType();
		sifStaff.setRefId(r1Staff.getStaffRefId());
		
		return sifStaff;
	}
	
	public R1Staff sifToModel(K12StaffType sifStaff)
	{
		R1Staff r1Staff = new R1Staff();
		r1Staff.setStaffRefId(sifStaff.getRefId());
		return r1Staff;
	}
	
}
