package utilities;

public class Config {
	
	public static String programId="0";
	public static int batchId=0;
	public static String userID = "";
	public static int assignmentID = 0;
	public static int submissionid=0;
	public static String adminUserID = "0";
	public static String staffUserID = "0";
	public static String studentUserID = "0";

	public static final String EXCEL = ".//Data Files/APIHackathonData.xlsx";
	public static final String BASE_URL = "http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	
	public static final String Getallprogram_URL = "http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/allPrograms";
	public static final String Getallassignment_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments";
	public static final String Getassignment_URL_BatchID = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments/batch";
	public static final String Postassignment_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments";
	public static final String Putassignment_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments/";
	public static final String Postassignmentinvalid_URL = "http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignm";
	
	public static final String GetProgramID_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/programs";
	public static final String POSTProgram_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";
	public static final String PUTProgram_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/putprogram";
	public static final String PUTProgramName_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/program";
	public static final String DELETEProgramName_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/deletebyprogname";
	public static final String DELETEProgramID_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/deletebyprogid";

	public static final String Getallsubmission_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission";
	public static final String Getallsubmissioninvalid_URL = "http://lms-api-hackathon.herokuapp.com/lms/assignmentsubmission";
	public static final String Getassignment_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/getGrades";
	public static final String DeleteUser_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users";
	
	public static final String Batch_SUB_URL = "batches";
	public static final String GETBatch_by_BatchID_URL = "batches/batchId/";
	public static final String GETBatch_by_BatchName_URL = "batches/batchName/";
	public static final String GETBatch_by_ProgramId_URL = "batches/program/";

	public static final String Getsubstudentid_URL = " http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/getGradesByStudentId";
	public static final String Getsubbatchid_URL = " http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/grades";
	public static final String Getsubbybatchid_URL = " http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/studentbatch";
	public static final String Getsubbyuserid_URL = " http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/student";
	public static final String Putresubmitassgn_URL ="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission";
	public static final String Putgradesubmit_URL ="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/gradesubmission";
	public static final String Delete_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission";
	public static final String Deleteinvalid_URL = "https://lms-api-hackathon-june2023.herokuapp.com/lms/assignmentsubmission";
	
	public static final String GetallUsers_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users";
	public static final String GetUsersallstaff_URL = "http://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/getAllStaff";
	public static final String GetUsersallroles_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/roles";
	public static final String GetallUsersinvalid_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/user";
	public static final String GetUserwithrolesinvalid_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/role";
	public static final String PostUser_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/roleStatus";
	public static final String PutUser_Rolestatus_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/roleStatus";
	public static final String PutUser_UserId_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users";
    public static final String PutUser_Batch_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/roleProgramBatchStatus";
}