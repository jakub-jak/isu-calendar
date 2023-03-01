import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
/*
 This is the year overview class. It prompts a user to select which year they want to select a month for.
 */
public class yearOverview extends JFrame{
    JFrame frame;

    JButton addMore;

    JPanel createMore, years;

    JScrollPane scrPane;

    public yearOverview(){

        // Initializing window and adding Jpanels

        super("Years");
        frame = new JFrame("MontYearshs");
        setSize(640, 700);

        Container totalGUI = getContentPane();
        totalGUI.setLayout(null);

        createMore = new JPanel();
        createMore.setSize(600, 70);
        createMore.setLocation(0, 0);

        years = new JPanel();
        years.setSize(580, 520);
        years.setLayout(new GridLayout(0, 3));

        scrPane = new JScrollPane(years);
        scrPane.setSize(580, 520);
        scrPane.setLocation(20, 70);

        totalGUI.add(createMore);
        totalGUI.add(scrPane);

        // Whenever new years are created, they are added to the global arraylist. This will check the size of that list and correspondingly generate that many years while
        // also directly naming that year with the i variable.

        for(int i = 0; i < mainInit.years.size(); i++){
            JButton tempButton;
            tempButton = new JButton("" + mainInit.years.get(i));
            tempButton.setName("" + mainInit.years.get(i));
            tempButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
            tempButton.setBackground(Color.white);
            tempButton.setPreferredSize(new Dimension(150, 50));
            years.add(tempButton);
            tempButton.addActionListener(e -> openYear(e));
        }

        // This button allows a user to create more years. Which will also create more years for the loop above.
    
        addMore = new JButton("Create More");
        addMore.addActionListener(e -> createMoreYears());
        addMore.setFont(new Font("Helvetica", Font.PLAIN, 15));
        addMore.setBackground(Color.white);
        addMore.setPreferredSize(new Dimension(150, 50));
        createMore.add(addMore);
    }
    // Opens a month overview with the year of the button, similarly uses the setName because variables cannot be passed as parameters in for loops
    public void openYear(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String name = b.getName();
        setVisible(false);
        new monthOverview(Integer.parseInt(name));
    }
    // A simple method to close the current window, generate more years, then open a new window
    public void createMoreYears(){
        setVisible(false);
        mainInit.generateYearMultiple(mainInit.currentYearIterable, 10);
        mainInit.yearList.setVisible(true);
    }
}