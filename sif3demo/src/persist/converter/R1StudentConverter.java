package persist.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sif.dd.us32.model.ObjectFactory;
import sif.dd.us32.model.K12StudentType;
import persist.model.R1Student;

@Component
public class R1StudentConverter extends HitsConverter<K12StudentType, R1Student> 
{

  public R1StudentConverter() 
  {
	  super(K12StudentType.class, R1Student.class);
  }


  //@Autowired
  //private PersonInfoConverter personInfoConverter;

  @Override
  public void toSifModel(R1Student source, K12StudentType target) 
  {
    if (source != null && target != null) 
    {
      target.setRefId(source.getStudentRefId());
    }
  }

  @Override
  public void toHitsModel(K12StudentType source, R1Student target) 
  {
    if (source != null && target != null) 
    {
      target.setStudentRefId(source.getRefId());    
    }
  }
}
