import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
/*
 The most important window, the main calendar overview.
 */
public class calendarOverview extends JFrame{
	JFrame frame;

	JPanel gradientPanel, calendarPanel, monthPanel, monthButtonBack, monthButtonForward, daysOfTheWeek;

	JLabel dayOfWeek;

	String monthName;
	int monthValue;
	int year;

	JButton serialize, deSerialize;
	
	
	
	public calendarOverview(int[] pkg) {
		
		// Initializing window and adding Jpanels

		super("Calendar");
		Container totalGUI = getContentPane();
		totalGUI.setLayout(null);

		monthName = mainInit.monthsNames[pkg[1] - 1];
		monthValue = pkg[1];
		year = pkg[2];

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame = new JFrame("Calendar");
		GridLayout gridLayout = new GridLayout(0, 7);
		setSize(1500, 1000);
		setLocation(0, 0);

		gradientPanel = new gradient(monthValue);
		gradientPanel.setSize(1500, 1000);

		calendarPanel = new JPanel();
		calendarPanel.setSize(800, 650);
		calendarPanel.setLayout(gridLayout);
		calendarPanel.setLocation(350, 170);
		calendarPanel.setOpaque(false);

		monthPanel = new JPanel();
		monthPanel.setSize(200, 80);
		monthPanel.setLocation(630, 20);
		monthPanel.setOpaque(false);

		monthButtonBack = new JPanel();
		monthButtonBack.setSize(100, 100);
		monthButtonBack.setLocation(530, 40);
		monthButtonBack.setOpaque(false);

		monthButtonForward = new JPanel();
		monthButtonForward.setSize(100, 100);
		monthButtonForward.setLocation(820, 40);
		monthButtonForward.setOpaque(false);

		daysOfTheWeek = new JPanel();
		daysOfTheWeek.setSize(800, 35);
		daysOfTheWeek.setLocation(343, 110);
		daysOfTheWeek.setLayout(new FlowLayout(0, 36, 0));
		daysOfTheWeek.setForeground(Color.WHITE);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		daysOfTheWeek.setBorder(blackline);

		totalGUI.add(daysOfTheWeek);
		totalGUI.add(calendarPanel);
		totalGUI.add(monthPanel);
		totalGUI.add(monthButtonBack);
		totalGUI.add(monthButtonForward);
		totalGUI.add(gradientPanel);

		// Calls a method called generateMonth that will format the first week of the month's calendar page
		
		generateMonth(pkg[0]);

		// Adding days of the week label

		JButton monthName = new JButton("" + mainInit.monthsNames[monthValue - 1] + " " + year);
		monthName.setFont(new Font("Helvetica", Font.PLAIN, 25));
		monthName.setBackground(Color.white);
		monthName.addActionListener(e -> exitToMonth());
		monthPanel.add(monthName);

		JButton monthButton2 = new JButton("Back");
		monthButton2.addActionListener(e -> changeMonthBackward());
		monthButton2.setFont(new Font("Helvetica", Font.PLAIN, 15));
		monthButton2.setBackground(Color.white);
		monthButtonBack.add(monthButton2);

		JButton monthButton = new JButton("Next");
		monthButton.addActionListener(e -> changeMonthForward());
		monthButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
		monthButton.setBackground(Color.white);
		monthButtonForward.add(monthButton);

		dayOfWeek = new JLabel("Sunday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);

		dayOfWeek = new JLabel("Monday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);

		dayOfWeek = new JLabel("Tuesday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);

		dayOfWeek = new JLabel("Wednesday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);
		
		dayOfWeek = new JLabel("Thursday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);

		dayOfWeek = new JLabel("Friday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);

		dayOfWeek = new JLabel("Saturday");
		dayOfWeek.setFont(new Font("Helvetica", Font.PLAIN, 19));
		daysOfTheWeek.add(dayOfWeek);
	}
	
	// generateMonth takes the day, and month (which will only be used to further pass to a different method)
	// this method checks if the day of the week is a certain value, 1-7, and if it isn't creates a blank label for padding, and if it is, creates the button
	
	public void generateMonth(int day) {
		for(int i = 0; i <= 7; i++){
			if(day == i){
				continueMonth();
				break;
			} else{
				JLabel temp = new JLabel("");
				temp.setOpaque(false);
				temp.repaint();
				calendarPanel.add(temp);
			}
		}
	}
	
	// continueMonth generates buttons for the rest of the month, with a limit determined by taking  the month's value and referencing it with \
	// the previously made monthsDays array
	
	public void continueMonth(){
		int limit = mainInit.monthsDays[monthValue - 1];
		for(int i = 0; i < limit; i++){
			if(limit == 28 && (year % 4) == 0){
				limit = 29;	
			}
			int listPos = mainInit.days.size();

			JButton newDay = new JButton("Day " + (i + 1));
			newDay.setBackground(Color.white);

			day dayVal = new day(monthName + " " + (i + 1) + " " + year, listPos, newDay);

			mainInit.days.add(dayVal);

			newDay.addActionListener(e -> goToDay(listPos));
			calendarPanel.add(newDay);

		}
	}
	// When the next month button is clicked, takes the current month and moves it forward. Also checks if the next month is not made, if it isnt, generates more years
	public void changeMonthForward(){
		setVisible(false);
		if (mainInit.currentMonth == (mainInit.calendars.size() - 1)){
			mainInit.generateYearMultiple(mainInit.currentYearIterable, 10);
		}
		mainInit.currentMonth++;
		mainInit.calendars.get(mainInit.currentMonth).setVisible(true);
	}
	// Moves the months back, makes sure can't go into a negative month
	public void changeMonthBackward(){
		if(mainInit.currentMonth == 0){
			return;
		} else {
			setVisible(false);
			mainInit.currentMonth --;
			mainInit.calendars.get(mainInit.currentMonth).setVisible(true);
		}
	}
	// Opens any individual day
	public void goToDay(int day){
		mainInit.days.get(day).setVisible(true);
	}
	// Opens a new month overview
	public void exitToMonth(){
		setVisible(false);
		new monthOverview(this.year);
	}
}