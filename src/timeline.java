import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
 This is the timeline panel that makes use of GridBagLayouts. By changing the gridwidth and gridx positions, single components can span multiple columns.
 */

public class timeline extends JFrame {
    JFrame frame;

    JPanel add, hint, times, scroll;

    JButton addEvent;

    JScrollPane scrPane;
    GridBagConstraints gbc;
    GridBagLayout gbl;

    Container totalGUI;

    JLabel tip;

    day dayPos;

    
    public timeline(day day){

        //Panel and GridBagConstraints are intialized here

        super("Timeline");

        dayPos = day;

        frame = new JFrame("Timeline");
        setSize(1500, 900);

        totalGUI = getContentPane();
        totalGUI.setLayout(null);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.weightx = 0.1;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.ipady = 40;
        gbc.ipadx = 40;

        times = new JPanel();
        times.setLayout(gbl);
        times.setLocation(0, 0);

        scrPane = new JScrollPane(times);
        scrPane.setSize(1400, 700);
        scrPane.setLocation(50, 10);

        add = new JPanel();
        add.setLayout(new FlowLayout());
        add.setSize(1500, 80);
        add.setLocation(0, 710);

        hint = new JPanel();
        hint.setLayout(new FlowLayout());
        hint.setSize(1500, 200);
        hint.setLocation(0, 790);

        totalGUI.add(scrPane);
        totalGUI.add(add);
        totalGUI.add(hint);

        // Here, buttons like adding a new event are added.
        
        addEvent = new JButton("Add Event");
        addEvent.addActionListener(e -> addEvent());
        addEvent.setPreferredSize(new Dimension(200, 60));
        addEvent.setBackground(new Color(169, 222, 249));
        add.add(addEvent);

        tip = new JLabel("Click on the events to remove them!");
        tip.setFont(new Font("Helvetica", Font.PLAIN, 25));
        hint.add(tip);

        // This long series of code is simply just setting up the hours atop the timeline bar.

        JLabel timePre = new JLabel("12:00\nAM", SwingConstants.CENTER);
        timePre.setBorder(blackline);
        timePre.setOpaque(true);
        timePre.setBackground(new Color(169, 222, 249));
        times.add(timePre, gbc);
        gbc.gridx++;
        for(int i = 1; i <= 11; i++){
            JLabel time = new JLabel("" + i + ":00" + "\nAM", SwingConstants.CENTER);
            time.setBorder(blackline);
            time.setOpaque(true);
            time.setBackground(new Color(169, 222, 249));
            times.add(time, gbc);
            gbc.gridx++;
        }
        timePre = new JLabel("12:00\nPM", SwingConstants.CENTER);
        timePre.setBorder(blackline);
        timePre.setOpaque(true);
        timePre.setBackground(new Color(169, 222, 249));
        times.add(timePre, gbc);
        gbc.gridx++;
        for(int i = 1; i <= 11; i++){
            JLabel time = new JLabel("" + i + ":00" + "\nPM", SwingConstants.CENTER);
            time.setBorder(blackline);
            time.setOpaque(true);
            time.setBackground(new Color(169, 222, 249));
            times.add(time, gbc);
            gbc.gridx++;
        }
        timePre = new JLabel("12:00\nAM", SwingConstants.CENTER);
        timePre.setBorder(blackline);
        timePre.setOpaque(true);
        timePre.setBackground(new Color(169, 222, 249));
        times.add(timePre, gbc);
        //
        gbc.weighty = 1.0;
        gbc.gridy = 1000;
        times.add(new JLabel(" "), gbc);

        gbc.ipadx = 0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
    }
    // This method calls the timelineadd class to add a new timeline.
    public void addEvent(){
        new timelineAdd(this);
        this.setVisible(false);
    }
}
