import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
/*
 This is the month overview. When the month is clicked on the main calendar overview, the user can easily select a new month or even change the year.
 */
public class monthOverview extends JFrame{
    JFrame frame;

    JPanel months, yearLabel;

    JButton yearDisplay;
    
    int year;

    public monthOverview(int passYear){

        // Initializing window and adding Jpanels

        super("Months");
        frame = new JFrame("Months");
        setSize(600, 600);

        Container totalGUI = getContentPane();
        totalGUI.setLayout(null);

        months = new JPanel();
        months.setLayout(new FlowLayout());
        months.setSize(600, 550);
        months.setLocation(0, 70);

        yearLabel = new JPanel();
        yearLabel.setSize(600, 70);
        yearLabel.setLocation(0, 0);

        totalGUI.add(months);
        totalGUI.add(yearLabel);

        year = passYear;

        // Since there are only 12 months in any year, this just goes through a for loop to add them

        for(int i = 1; i <= 12; i++){
            JButton tempMonth = new JButton(mainInit.monthsNames[i - 1]);
            tempMonth.setName("" + i);
            tempMonth.setFont(new Font("Helvetica", Font.PLAIN, 15));
            tempMonth.setBackground(Color.white);
            tempMonth.setPreferredSize(new Dimension(120, 50));
            tempMonth.addActionListener(e -> goToMonthBuffer(e));
            months.add(tempMonth);
        }
// This adds a button for this current year that when clicked will open the year overview
        yearDisplay = new JButton("" + year);
        yearDisplay.addActionListener(e -> goToYear());
        yearDisplay.setFont(new Font("Helvetica", Font.PLAIN, 15));
        yearDisplay.setBackground(Color.white);
        yearDisplay.setPreferredSize(new Dimension(120, 50));
        yearLabel.add(yearDisplay);

        setVisible(true);
    }
    // This will open the month corresponding to this year with a simple formula to calculate its list position
    public void goToMonth(int value){

        setVisible(false);

        int listPos = ((((year - mainInit.initYear) * 12) + value) - 1);

        mainInit.currentMonth = listPos;

        mainInit.calendars.get(mainInit.currentMonth).setVisible(true);
    }
    // This will open a year overview to select different years
    public void goToYear(){
        setVisible(false);
        mainInit.yearList.setVisible(true);
    }
    // Because the buttons are added in for loops, they cannot be have a variable passed through them as a parameter
    // The workaround is setting the month as the name of the button, and that is accessed here before being passed to the go to month method
    public void goToMonthBuffer(ActionEvent e){
        JButton j = (JButton)e.getSource();
        goToMonth(Integer.parseInt(j.getName()));
    }
}
