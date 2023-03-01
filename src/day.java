import java.awt.*;
import javax.swing.*;
/*
 This window is an overview of the day. It shows the notes textfield, and reminders textfield, where text can be stored, saved, and later retrieved.
 */
public class day extends JFrame{
    JFrame frame;

    JPanel title, labels, timeline, notes, updateButtons, reminders;

    TextArea notesText, remindersText;
    JLabel dates, remindersLabel, notesLabel;

    JButton updateText, updateText2, toTimeline, calButton;

    timeline timelineThis = new timeline(this);

    int listPos;
    public day(String date, int pos, JButton button){

        // Initializing window and adding Jpanels

        super("Day");

        listPos = pos;
        calButton = button;

        Container totalGUI = getContentPane();
        totalGUI.setLayout(null);

        frame = new JFrame("Day");
        setSize(920, 900);
        setLocation(0, 0);

        title = new JPanel();
        title.setSize(250, 40);
        title.setLocation(320, 10);
        title.setLayout(new FlowLayout());

        timeline = new JPanel();
        timeline.setSize(200, 100);
        timeline.setLocation(700, 10);

        notes = new JPanel();
        notes.setSize(470, 600);
        notes.setLocation(0, 130);

        updateButtons = new JPanel();
        updateButtons.setSize(800, 400);
        updateButtons.setLocation(0, 730);

        reminders = new JPanel();
        reminders.setSize(500, 600);
        reminders.setLocation(430, 130);

        labels = new JPanel();
        labels.setSize(905, 50);
        labels.setLocation(10, 80);
        labels.setLayout(new FlowLayout(0, 180, 0));

        totalGUI.add(labels);
        totalGUI.add(title);
        totalGUI.add(timeline);
        totalGUI.add(notes);
        totalGUI.add(reminders);
        totalGUI.add(updateButtons);

        // To create my own custom layout, I had to get creative with using empty rigid boxes, and carefully adjusting size and location of jpanels.

        dates = new JLabel(date);
        dates.setFont(new Font("Helvetica", Font.PLAIN, 30));
        title.add(dates);

        toTimeline = new JButton("Timeline");
        toTimeline.addActionListener(e -> toTimeline());
        toTimeline.setPreferredSize(new Dimension(150, 50));
        toTimeline.setBackground(new Color(169, 222, 249));
        timeline.add(toTimeline);

        notesLabel = new JLabel("Notes");
        notesLabel.setFont(new Font("Helvetica", Font.PLAIN, 25));
        labels.add(notesLabel);

        labels.add(Box.createRigidArea(new Dimension(1, 0)));

        remindersLabel = new JLabel("Reminders");
        remindersLabel.setFont(new Font("Helvetica", Font.PLAIN, 25));
        labels.add(remindersLabel);

        notesText = new TextArea("", 38, 50);
        notes.add(notesText);

        remindersText = new TextArea("", 38, 50);
        reminders.add(remindersText);

        updateButtons.add(Box.createRigidArea(new Dimension(70, 0)));

        updateText = new JButton("Update");
        updateText.addActionListener(e -> updateText("1"));
        updateText.setPreferredSize(new Dimension(200, 60));
        updateText.setBackground(new Color(169, 222, 249));
        updateButtons.add(updateText);

        updateButtons.add(Box.createRigidArea(new Dimension(250, 0)));

        updateText2 = new JButton("Update");
        updateText2.addActionListener(e -> updateText("2"));
        updateText2.setPreferredSize(new Dimension(200, 60));
        updateText2.setBackground(new Color(169, 222, 249));
        updateButtons.add(updateText2);
    }
    // This button takes the text, along with day position and what type of text field it is when saving it
    public void updateText(String number){
        String[] pkg = {"", "", ""};
        if(number.equals("1")){
            pkg = new String[]{"" + listPos, this.notesText.getText(), number};
        } else{
            pkg = new String[]{"" + listPos, this.remindersText.getText(), number};
        }
        mainInit.daysText.add(pkg);
        mainInit.serialize();
    }
    // This opens a new timeline window
    public void toTimeline(){
        timelineThis.setVisible(true);
    }
}
