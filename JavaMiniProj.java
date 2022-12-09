import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.time.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.System;

class ClockDisplay extends JFrame implements ActionListener {
    Container c;
    JTextField t1;
    JButton b1, b2, b3, b4, b5, b6;
    JLabel l1, l2, l3;
    Timer timer;
    int flag = 1, flag2 = 1;

    ClockDisplay() {
        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        l1 = new JLabel("Clock Display");
        l2 = new JLabel("Timer Display");
        l3 = new JLabel("Stopwatch");
        b1 = new JButton("Clock");
        b2 = new JButton("Timer");
        b3 = new JButton("StartWatch");
        b4 = new JButton("StopWatch");
        b5 = new JButton("Continue");
        b6 = new JButton("Reset");
        t1 = new JTextField("Enter no. of seconds");
        c.add(l1);
        c.add(b1);
        c.add(l2);
        c.add(t1);
        c.add(b2);
        c.add(l3);
        c.add(b3);
        c.add(b4);
        c.add(b5);
        c.add(b6);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
    }

    public void startClock() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                l1.setText(formatter.format(date));
            }
        });
        timer.start();
    }

    public void startTimer(int time, int delay) {
        timer = new Timer(delay, new ActionListener() {
            int s = time;

            public void actionPerformed(ActionEvent ae) {
                l2.setText(Integer.toString(s));

                if (s <= 0) {
                    l2.setText("Timer completed");
                    timer.stop();
                    flag = 1;
                }
                s--;
            }
        });

        timer.start();
    }

    public void startStopwatch(int time, int delay) {
        timer = new Timer(delay, new ActionListener() {
            float s = time;

            public void actionPerformed(ActionEvent ae) {
                l3.setText(Float.toString(s / 100) + " seconds");
                s++;
            }
        });

        timer.start();
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1)
            startClock();
        if (ae.getSource() == b2 && flag == 1) {
            flag = 0;
            int time = Integer.parseInt(t1.getText());
            startTimer(time, 1000);
        }

        if (ae.getSource() == b3 && (flag2 == 1)) {
            flag2 = 0;
            int time = 0;
            startStopwatch(time, 1);
        }
        if (ae.getSource() == b4) {

            timer.stop();
        }
        if (ae.getSource() == b5 && (flag2 == 0)) {

            timer.restart();
        }
        if (ae.getSource() == b6 && (flag2 == 0)) {

            timer.stop();
            l3.setText("0");
            flag2 = 1;
        }
    }
}

public class JavaMiniProj {

    public static void main(String[] args) {
        ClockDisplay cd = new ClockDisplay();
        cd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cd.setBounds(200, 200, 400, 400);
        cd.setVisible(true);
        cd.setTitle("Clock Application");
    }
}
