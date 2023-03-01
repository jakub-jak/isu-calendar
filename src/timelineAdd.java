import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.Border;
/*
 This window allows users to add new timelines to the timeline window.
 */
public class timelineAdd extends JFrame{
    ArrayList<String> timesArrayList = new ArrayList<String>();
    String[] timesList = {"12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM", "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM (Next Day)"};
    
    JFrame frame;

    JComboBox<String> timeBeginC, timeEndC;

    JPanel names, times, colorPicker, add;

    JTextField input;
    
    JLabel name, timeBegin, timeEnd;

    JButton addEvent, colorOpen;

    static Color color;

    static timeline ref;
    public timelineAdd(timeline timelineObj){

// Initializing window and adding Jpanels

        super("Timeline");

        populateArrayList();

        color = Color.red;

        frame = new JFrame("Timeline");
        setSize(500, 500);
        
        ref = timelineObj;

        Container totalGUI = getContentPane();
        totalGUI.setLayout(null);

        names = new JPanel();
        names.setSize(500, 50);
        names.setLocation(0, 0);
        names.setLayout(new FlowLayout());

        times = new JPanel();
        times.setSize(500, 50);
        times.setLocation(0, 50);
        times.setLayout(new FlowLayout());

        colorPicker = new JPanel();
        colorPicker.setSize(500, 50);
        colorPicker.setLocation(0, 100);
        colorPicker.setLayout(new FlowLayout());

        add = new JPanel();
        add.setSize(500, 50);
        add.setLocation(0, 150);
        add.setLayout(new FlowLayout());

        totalGUI.add(names);
        totalGUI.add(times);
        totalGUI.add(colorPicker);
        totalGUI.add(add);

        // Adding buttons and labels

        name = new JLabel("Enter the event name:");
        input = new JTextField(15);

        names.add(name);
        names.add(input);

        timeBegin = new JLabel("Event Begin");
        timeEnd = new JLabel("Event End");

        timeBeginC = new JComboBox<String>(timesList);
        timeEndC = new JComboBox<String>(timesList);
        
        times.add(timeBegin);
        times.add(timeBeginC);

        times.add(Box.createRigidArea(new Dimension(10, 0)));

        times.add(timeEnd);
        times.add(timeEndC);

        colorOpen = new JButton("Pick a colour");
        colorOpen.addActionListener(e -> toColor());
        colorPicker.add(colorOpen);

        addEvent = new JButton("Add Event");
        addEvent.addActionListener(e -> update());

        add.add(addEvent);

        setVisible(true);
    }
    // When the add event is clicked, the values from the combo boxes and other components are passed through with this method
    // calls a different method to add the values to the timeline
    public void update(){
        String temp = String.valueOf(timeBeginC.getSelectedItem());
        String temp2 = String.valueOf(timeEndC.getSelectedItem());
        int length = timesArrayList.indexOf(temp2) - timesArrayList.indexOf(temp);
        if(length > 0){
            setVisible(false);
            addTime(ref, timesArrayList.indexOf(temp), length, input.getText(), color);
            ref.setVisible(true);
        } else{
            // Makes sure that the two times are not impossible
            JOptionPane.showMessageDialog(null, "Error: The times you have selected are invalid!");
        }

    }
    // A simple method to populate an arraylist of times (thats used for comparing with the Jcomboboxes) rather than manually popualating the array
    public void populateArrayList(){
        timesArrayList.add("12:00 AM");
        for(int i = 1; i <= 11; i++){
            timesArrayList.add("" + i + ":00 " + "AM");
        }
        timesArrayList.add("12:00 PM");
        for(int i = 1; i <= 11; i++){
            timesArrayList.add("" + i + ":00 " + "PM");
        }
        timesArrayList.add("12:00 AM (Next Day)");
    }

    // A method to simplify adding buttons with a gridbaglayout, also updates the global arraylist before saving it
    public static void addTime(timeline refer, int gridx, int gridwidth, String label, Color colorChoice){
        ref = refer;

        refer.gbc.fill = GridBagConstraints.HORIZONTAL;

        refer.gbc.weightx = 0.1;

        refer.gbc.gridx = gridx;

        if(gridwidth == 0){
            gridwidth = 1;
        }

        refer.gbc.gridwidth = gridwidth;

        JButton tempLabel = new JButton(label);

        tempLabel.setOpaque(true);
        tempLabel.setBackground(colorChoice);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        tempLabel.setBorder(blackline);

        refer.times.add(tempLabel, refer.gbc);

        String[] tempString = {"" + refer.dayPos.listPos, "" + gridx, "" + gridwidth, label, "" + colorChoice.getRGB()};

        tempLabel.addActionListener(e -> removeButton(tempLabel, tempString));

        mainInit.timelineSave.add(tempString);
        mainInit.serializeTimeline();

        refer.gbc.gridy++;
    }
    // Allows the buttons to be removed from the container, the arraylist, and refreshes the window
    public static void removeButton(JButton button, String[] stringPos){
        ref.times.remove(button);
        ref.times.revalidate();
        ref.times.repaint();

        mainInit.timelineSave.remove(stringPos);
        mainInit.serializeTimeline();
    }
    // A color picker for selecting the timeline color of each event
    public void toColor(){ 
        color = JColorChooser.showDialog(null, "Pick a color", Color.white);
    }
}
