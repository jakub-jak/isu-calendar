// Author: Jakub Jakuszewski
/* 
 This program is a calendar that can save notes, reminders and even schedule personal events! This program can highlight the current day,
 and generate plenty of years of storage!
*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
import java.awt.Color;
public class mainInit {
	// Initializes ArrayLists and variables that will later be used for global comparison or for saving to be used upon program reopening
	public static int[] monthsDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public static ArrayList<Integer> years = new ArrayList<Integer>();
	public static ArrayList<calendarOverview> calendars = new ArrayList<calendarOverview>();
	public static ArrayList<day> days = new ArrayList<day>();
	public static ArrayList<String[]> daysText = new ArrayList<String[]>();

	public static ArrayList<String[]> timelineSave = new ArrayList<String[]>();

	public static int yearDiff = 0;

	public static int currentMonth = 0;

	public static yearOverview yearList;

	public static int initYear;
	public static int currentYearIterable;

	public static void main(String[] args) {
		
		// Gets the current year for program initialization
		initYear = LocalDateTime.now().getYear();
		currentMonth = LocalDateTime.now().getMonthValue() - 1;

		// Attempt to open saved data of a range of years generated, if none, will generate 10 years from the current year opened at.
		try {
			deSerializeYears();
		} catch(Exception e) {}

		// Either way, the try and catch will set variables that will be used to generate the years
		generateYearMultiple(currentYearIterable, yearDiff);

		// This remainder will take the current program open year and the saved initialization year of the program to find a difference that will be
		// used to calculate how many days or months to iterate through the global arraylist
		int remainder = LocalDateTime.now().getYear() - initYear;

		calendars.get(remainder * 12 + currentMonth).setVisible(true);
		currentMonth = remainder * 12 + currentMonth;

		// This attempts to open any saved days or timelines created
		try {
			deSerialize();
			deSerializeTimeline();
		} catch(Exception e){}

		
		int listPos = 0;
		for(int i = 0; i < remainder; i++){
            if((initYear + i) % 4 == 0){
                listPos += 366;
            } else {
                listPos += 365;
            }
        }
	
		// Finds the current day and highlights it on the calendar

		listPos += LocalDateTime.now().getDayOfYear() - 1;

		days.get(listPos).calButton.setBackground(Color.yellow);
		
	}
	// Method to call for the argument of the calendar creation method, with an int array because of multiple return values
	public static int[] updateMonth(int month, int year){
		String str;
		
		// Checks if the month being passed as an argument is greater than or less than 10, which affects the way the string is formatted
		// Formats the string for the month being passed
		
		if (month < 10){
			str = "" + year + "-0" + month + "-01 00:01";
		} else{
			str = "" + year + "-" + month + "-01 00:01";
		}
		// Takes the string and converts it into a LocalDateTime class that can be used to find the day of the week of the first day of that month
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		int day = dateTime.getDayOfWeek().getValue();
		
		// Returns day of the week and month in the int array
		
		return new int[]{day, month, year};
	}
	// Adds a new calendar class to a global arraylist and passes a parameter that was made with the previous method
	// Creates 12 months
	public static void generateYear(int year){
		for(int i = 1; i <= 12; i++){
			calendars.add(new calendarOverview(updateMonth(i, year)));
		}
	}
	// Simply put, can take parameters to generate multiple years
	public static void generateYearMultiple(int year, int limit){
		for(int i = 0; i <= limit; i++){
			years.add(year);
			generateYear(year++);
			currentYearIterable = year; 
		}
		// Also saves the year and their range
		yearList = new yearOverview();
		yearDiff = currentYearIterable - initYear - 1;
		serializeYears();
	}
	// Method to save days
	public static void serialize(){
		try {
			FileOutputStream fileOut = new FileOutputStream("days");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(daysText);
			out.close();
			fileOut.close();
		 } catch (Exception i) {}
	}
	// Method to open days, essentially when days are saved, they are saved as string arrays with all the information of their position within the global day arraylist,
	// their contents, etc.
	@SuppressWarnings("unchecked")
	public static void deSerialize() {
		try {
			FileInputStream fileIn = new FileInputStream("days");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<String[]> temp = (ArrayList<String[]>)in.readObject();
			in.close();
			fileIn.close();
			daysText = temp;
			for(int i = 0; i < temp.size(); i++){
				String[] currentDay = temp.get(i);
				if(currentDay[2].equals("1")){
					days.get(Integer.parseInt(currentDay[0])).notesText.setText(currentDay[1]);
				} else{
					days.get(Integer.parseInt(currentDay[0])).remindersText.setText(currentDay[1]);
				}
				
			}
		} catch (Exception e){}
	}
	// Saves the year range
	public static void serializeYears() {
		try {
			FileOutputStream fileOut = new FileOutputStream("yearDiff");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(yearDiff);
			out.close();
			fileOut.close();
		 } catch (IOException i) {}
	}
	// Used to save the programs first recognized year when it was intialized
	public static void serializeInitYear() {
		try{
			FileOutputStream fileOut = new FileOutputStream("initYear");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(initYear);
			out.close();
			fileOut.close();
		} catch(Exception e){}
	}
	// Opens years from file, if there is not a file found generates them instead
	public static void deSerializeYears() {
		try {
			FileInputStream fileIn = new FileInputStream("yearDiff");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			yearDiff = (Integer)in.readObject();
			in.close();
			fileIn.close();
			
			fileIn = new FileInputStream("initYear");
			in = new ObjectInputStream(fileIn);
			initYear = (Integer)in.readObject();
			in.close();
			fileIn.close();

			currentYearIterable = initYear;
		} catch (Exception e){
			yearDiff = 10;
			currentYearIterable = initYear;

			serializeInitYear();
		}
	}
	// Saves timelines
	public static void serializeTimeline() {
		try {
			FileOutputStream fileOut = new FileOutputStream("timeline");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(timelineSave);
			out.close();
			fileOut.close();
		} catch (Exception e){}
	}
	// Opens timelines and finds their position with the global days arraylist and fills the timelines with the neccesarry info from the array
	@SuppressWarnings("unchecked")
	public static void deSerializeTimeline() {
		try{
			FileInputStream fileIn = new FileInputStream("timeline");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<String[]> timelineSaveTemp = (ArrayList<String[]>)in.readObject();
			in.close();
			fileIn.close();

			for(int i = 0; i < timelineSaveTemp.size(); i++){
				String[] temp = timelineSaveTemp.get(i);

				int dayPos = Integer.parseInt(temp[0]);
				int gridx = Integer.parseInt(temp[1]);
				int gridwidth = Integer.parseInt(temp[2]);
				String name = temp[3];
				Color color = new Color(Integer.parseInt(temp[4]));

				timeline tempTime = days.get(dayPos).timelineThis;
				
				timelineAdd.addTime(tempTime, gridx, gridwidth, name, color);
			}
		} catch(Exception e){}
	}
}