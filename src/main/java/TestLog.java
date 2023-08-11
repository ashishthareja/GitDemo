import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.*;


public class TestLog  {
	
	public static String NextWeekTimestamp() {
        
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");            
		Calendar c = Calendar.getInstance();        
		c.add(Calendar.DATE, 7);  // number of days to add      
		String timestamp = (String)(formattedDate.format(c.getTime()));
		System.out.println("Tomorrows date is " + timestamp);
		
		return timestamp;
	}
	
	public static String MonthYear() {
		SimpleDateFormat df = new SimpleDateFormat("MMMyy");
		String timestamp = df.format(new Date())  ;
		return (timestamp);
	}

	private static Logger testLogger = LogManager.getLogger();
	public static void main(String[] args) {
		testLogger.info("Test Logging");
		testLogger.info(MonthYear());

	}

}
