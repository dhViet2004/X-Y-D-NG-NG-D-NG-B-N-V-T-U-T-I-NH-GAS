/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class TimePickerExample extends JFrame {
    private JSpinner spinnerHour;
    private JSpinner spinnerMinute;
    private JSpinner spinnerSecond;

    public TimePickerExample() {
        setTitle("Time Picker");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Spinner for hours
        spinnerHour = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));  // Hour from 0 to 23
        spinnerMinute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));  // Minute from 0 to 59
        spinnerSecond = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));  // Second from 0 to 59

        add(new JLabel("Hour: "));
        add(spinnerHour);
        add(new JLabel("Minute: "));
        add(spinnerMinute);
        add(new JLabel("Second: "));
        add(spinnerSecond);

        JButton getTimeButton = new JButton("Get Time");
        getTimeButton.addActionListener(e -> getSelectedTime());

        add(getTimeButton);
    }

    public void getSelectedTime() {
        int hour = (int) spinnerHour.getValue();
        int minute = (int) spinnerMinute.getValue();
        int second = (int) spinnerSecond.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        Date selectedTime = calendar.getTime();
        System.out.println("Selected Time: " + selectedTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimePickerExample picker = new TimePickerExample();
            picker.setVisible(true);
        });
    }
}