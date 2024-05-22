package gymPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {
    private JButton closeButton;
    private JButton okButton;
    private JTextField tfWeight;
    private JTextField tfHeight;

    public MainWindow() {
        // Set up the window
        setSize(800, 600);
        setTitle("GymApp");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tfWeight = new JTextField();
        tfWeight.setBounds(300, 200, 200, 25);
        tfHeight = new JTextField();
        tfHeight.setBounds(300, 300, 200, 25);
        
        JLabel lW = new JLabel("Weight");
        lW.setBounds(250, 200, 50, 25);
        JLabel lH = new JLabel("Height");
        lH.setBounds(250, 300, 50, 25);
        JLabel bmi = new JLabel("Current BMI: " + DatabaseUtils.getCurrentBMI());
		bmi.setBounds(325, 160, 300, 25);


        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        closeButton.setBounds(670, 525, 100, 25);
        
        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        okButton.setBounds(550, 300, 100, 25);

        add(closeButton);
        add(okButton);
        add(tfWeight);
        add(tfHeight);
        add(lW);
        add(lH);
        add(bmi);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == closeButton) {
            System.exit(0);
        }
        if (ae.getSource() == okButton) {
            try {
                String weight = tfWeight.getText();
                int intWeight = Integer.parseInt(weight);
                String height = tfHeight.getText();
                int intHeight = Integer.parseInt(height);
                DatabaseUtils.insertBodyInformation(intWeight, intHeight);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for weight and height.");
            }
        }
    }
}
