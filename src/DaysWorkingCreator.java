import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

public class DaysWorkingCreator {

	static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	static final int[] DAYS_TOTAL = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "Auguest", 
			"September", "October", "November", "December"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int year = Integer.parseInt(args[0]);
		//String print = args[1];
		//add first day of week selector (sun/mon)?
		int year = 2020;
		
		if(isLeapYear(year)){
			DAYS_TOTAL[1] = 29;
		}
		
		//screenPrint(year);
		filePrint(year);
		
	}//end main

	private static void filePrint(int year) {
		// TODO Auto-generated method stub
		int start = findStart(year);
		
		try {
			PrintWriter writer = new PrintWriter("DaysWorking" + year,"UTF-8");
			writer.println("~" + year + "~");
			for(int month = 0; month < DAYS_TOTAL.length; month++){
				writer.println("~" + MONTHS[month]);
				for(int dayNumber = 1; dayNumber <= DAYS_TOTAL[month]; dayNumber++){
					String formattedDay = String.format("%02d", dayNumber);
					writer.println(formattedDay + " " + DAYS[start] + "  | ");
					if(start == 6){
						start = 0;
						writer.println();
					}else{
						start++;
					}//end ifelse
				}//end dayNumber loop
			}//end month loop
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void screenPrint(int year) {
		// TODO Auto-generated method stub
		
		int start = findStart(year);
		
		System.out.println("~" + year + "~");
		for(int month = 0; month < DAYS_TOTAL.length; month++){
			System.out.println("~" + MONTHS[month]);
			for(int dayNumber = 1; dayNumber <= DAYS_TOTAL[month]; dayNumber++){
				String formattedDay = String.format("%02d", dayNumber);
				System.out.println(formattedDay + " " + DAYS[start] + "  | ");
				if(start == 6){
					start = 0;
					System.out.println();
				}else{
					start++;
				}//end ifelse
			}//end dayNumber loop
		}//end month loop
	}

	private static int findStart(int year) {
		int start = -1;
		
		DayOfWeek day = LocalDate.parse(year + "-01-01").getDayOfWeek();
		
		switch(day.toString()){
			case "SUNDAY": start = 0;
				break;
			case "MONDAY": start = 1;
				break;
			case "TUESDAY": start = 2;
				break;
			case "WEDNESDAY": start = 3;
				break;
			case "THURSDAY": start = 4;
				break;
			case "FRIDAY": start = 5;
				break;
			case "SATURDAY": start = 6;
				break;
		}
		
		return start;
	}

	public static boolean isLeapYear(int year) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.YEAR, year);
		  return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
		}
}
