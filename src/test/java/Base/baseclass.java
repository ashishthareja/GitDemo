package Base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Excelreader;

public class baseclass {

	static Logger logger = LogManager.getLogger("BaseClass.java");
	Excelreader ed = new Excelreader();

	// Method to generate random string unto 3 digit for invalidID 
	public static String randomestring() {
		logger.info("randomestring method");
		String generateinvalidID = RandomStringUtils.randomNumeric(3);
		return (generateinvalidID);
	}

	public static String Timestamp() {
		logger.info("Timestamp method");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String timestamp = df.format(new Date())  ;
		return (timestamp);
	}
	
	// Method for generate date for Submission Module 
	public static String DiffTimestamp() {
		logger.info("DiffTimestamp method");
		SimpleDateFormat datetime = new SimpleDateFormat("dd-MM-yyyy mm:ss:HH");
		String timestamp = datetime.format(new Date());
		return (timestamp);
	}
	
	// Method for generate date for Assignment Name 
	public static String MonthYear() {
		logger.info("MonthYear method");
		SimpleDateFormat df = new SimpleDateFormat("MMMyy");
		String timestamp = df.format(new Date())  ;
		return (timestamp);
	}
	
	// Method to Get Submission Date for Assignment module 
	public static String NextWeekTimestamp() {   
		logger.info("NextWeekTimestamp method");
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");            
		Calendar c = Calendar.getInstance();        
		c.add(Calendar.DATE, 7);  // number of days to add      
		String timestamp = (String)(formattedDate.format(c.getTime()));
		return timestamp;
	}
	
	// Method to generate the Assignment Name 
	public static String AssignmentName() {
		logger.info("AssignmentName method");
		String name = MonthYear()+"-BugBusters-SDET-API-"+randomestring();
		return (name);
	}
	
    // Extract the data from Excel and return the value as String
	public static String excelDataValue(String sheetName, String testCase) throws IOException {
		logger.info("String excelDataValue method");
		Excelreader ed = new Excelreader();
		ArrayList<String> data;
		data = ed.dataDriven(sheetName, testCase);
		// read column value from index1
		String value = data.get(1);
		return value;
	}
	// Extract the data from Excel and return the value as ArrayList
	public static ArrayList<String> excelValue(String sheetName, String testCase) throws IOException {
		logger.info("ArrayList excelValuemethod");
		Excelreader ed = new Excelreader();
		ArrayList<String> data = ed.dataDriven(sheetName, testCase);
		System.out.println("PrintvaluesfromExcel"+data);
		return data;
	}
}
