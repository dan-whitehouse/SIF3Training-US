package sif3demo.service;
import java.util.ArrayList;
import java.util.List;

import sif.dd.us32.conversion.DataModelUnmarshalFactory;
import sif.dd.us32.model.K12StudentCollectionType;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.PagingInfo;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.rest.consumer.ConsumerLoader;
import sif3demo.consumer.StudentConsumer;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 * 
 */


public class DemoConsumer
{
//	private final static String SINGLE_STUDENT_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3Training-US/TestData/xml/input/StudentPersonal.xml";
//	private final static String MULTI_STUDENT_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3Training-US/TestData/xml/input/K12Students2.xml";
//	private final static String SINGLE_STUDENT_FILE_NAME = "C:/Development/GitHubRepositories/SIF3Training-US/SIF3Training-US/TestData/xml/input/StudentPersonal.xml";
	private final static String MULTI_STUDENT_FILE_NAME = "C:/Users/daniel.whitehouse/workspace/SIF3Training-US/TestData/xml/input/K12Students2.xml";
	private static final String CONSUMER_ID = "StudentConsumer";

	private static final RequestType REQUEST_TYPE = RequestType.IMMEDIATE;

	private void printResponses(List<Response> responses, StudentConsumer consumer)
	{
		try
		{
			if (responses != null)
			{
				int i = 1;
				for (Response response : responses)
				{
					System.out.println("Response " + i + ":\n" + response);
					if (response.hasError())
					{
						System.out.println("Error for Response " + i + ": " + response.getError());
					}
					else
					// We should have a student personal
					{
						if (response.getHasEntity())
						{
							System.out.println("Data Object Response " + i + ": " + consumer.getMarshaller().marshalToXML(response.getDataObject()));
						}
						else
						{
							System.out.println("Data Object Response " + i + ": No Data Returned. Respnse Status = " + response.getStatus() + " (" + response.getStatusMessage() + ")");
						}
					}
					i++;
				}
			}
			else
			{
				System.out.println("Responses from attempt to create Student: null");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private K12StudentCollectionType loadStudents(DataModelUnmarshalFactory unmarshaller)
	{
		String inputEnvXML = FileReaderWriter.getFileContent(MULTI_STUDENT_FILE_NAME);
		try
		{
			return (K12StudentCollectionType) unmarshaller.unmarshalFromXML(inputEnvXML, K12StudentCollectionType.class);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private StudentConsumer getConsumer()
	{
		return new StudentConsumer();
	}

	/*
	 * ----------------------- 
	 * Section for Exercise 2 
	 * ------------------------
	 */
	/*
	 * Possible Solution for Exercise 2
	 */
	private void getStudents(StudentConsumer consumer)
	{
		System.out.println("Start 'Get List of Students' ...");

		try
		{
			List<Response> responses = consumer.retrieve(new PagingInfo(5, 1), null, REQUEST_TYPE);
			System.out.println("Responses from attempt to Get All Students:");
			printResponses(responses, consumer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Finished 'Get List of Students'.");
	}

	/*
	 * Possible Solution for Exercise 2
	 */
	private void getStudent(StudentConsumer consumer)
	{
		System.out.println("Start 'Get Single Student'...");

		try
		{
			List<Response> responses = consumer.retrievByPrimaryKey("abbb4567-0E11-40ee-3aDD-fD1cBb6B1001", null);
			System.out.println("Responses from attempt to Get Student:");
			printResponses(responses, consumer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		System.out.println("Finished 'Get Single Student'.");
	}

	/*
	 * ----------------------------- 
	 * End of Section for Exercise 2 
	 * -------------------------------
	 */

	/*
	 * ----------------------- 
	 * Section for Exercise 4 
	 * ------------------------
	 */
	/*
	 * Possible Solution for Exercise 4
	 */
	private void deleteStudents(StudentConsumer consumer)
	{
		System.out.println("Start 'Delete List of Students' ...");

		// Create an ArrayList of 5 GUIDs.
	    ArrayList<String> resourceIDs = new ArrayList<String>();
	    resourceIDs.add(UUIDGenerator.getUUID());
		resourceIDs.add(UUIDGenerator.getUUID());
		resourceIDs.add(UUIDGenerator.getUUID());
		resourceIDs.add(UUIDGenerator.getUUID());
		resourceIDs.add(UUIDGenerator.getUUID());
	    
	    try
	    {
	      List<BulkOperationResponse<OperationStatus>> responses = consumer.deleteMany(resourceIDs, null, REQUEST_TYPE);

	      // Print Response to deleteMany to the Screen...
	      if (responses != null)
	      {
	        int i = 1;
	        for (BulkOperationResponse<OperationStatus> response : responses)
	        {
	          System.out.println("Response "+i+":\n"+response);
	          i++;
	        }
	      }
	      else
	      {
	        System.out.println("Responses from attempt to delete Students: null");        
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }

		System.out.println("Finished 'Delete List of Students'.");
	}

	/*
	 * Possible Solution for Exercise 4
	 */
	private void updateStudent(StudentConsumer consumer)
	{
		System.out.println("Start 'Update List of Students'...");

		// Load a set of students from a file. Ensure that MULTI_STUDENT_FILE_NAME constant at the 
		// top of this class points to the correct location where the StudentPersonals5.xml is located.
	    K12StudentCollectionType students = loadStudents((DataModelUnmarshalFactory)consumer.getUnmarshaller());
	    try
	    {
	      List<BulkOperationResponse<OperationStatus>> responses = consumer.updateMany(students, null, REQUEST_TYPE);

	      // Print Response to deleteMany to the Screen...
	      if (responses != null)
	      {
	        int i = 1;
	        for (BulkOperationResponse<OperationStatus> response : responses)
	        {
	          System.out.println("Response "+i+":\n"+response);
	          if (response.hasError())
	          {
	            System.out.println("Error for Response "+i+": "+response.getError());
	          }
	          else // We should have a student personal
	          {
	            System.out.println("Student Response "+i+": "+response.getOperationStatuses());
	          }
	          i++;
	        }
	      }
	      else
	      {
	        System.out.println("Responses from attempt to update Students: null");        
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }

		System.out.println("Finished 'Update List of Students'.");
	}

	/*
	 * ----------------------------- 
	 * End of Section for Exercise 4 
	 * -------------------------------
	 */

	public static void main(String[] args)
	{
		DemoConsumer demo = new DemoConsumer();
		System.out.println("Start DemoConsumer...");

		if (ConsumerLoader.initialise(CONSUMER_ID))
		{
			StudentConsumer consumer = demo.getConsumer();

			// Use for Exercise 2
			//demo.getStudent(consumer);
			//demo.getStudents(consumer);
			

			// Use for Exercise 4 - uncomment the 2 lines below
			//demo.deleteStudents(consumer);
			demo.updateStudent(consumer);
		}

		ConsumerLoader.shutdown();
		System.out.println("End DemoConsumer.");
	}

}
