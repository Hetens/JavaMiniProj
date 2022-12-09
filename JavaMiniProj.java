import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.plaf.DimensionUIResource;

import java.time.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


class ClockDisplay extends JFrame implements ActionListener {
    Container d;
    JPanel c,c1,c2,c3,c4;
    JTextField t1;
    JButton b1, b2, b3, b4, b5, b6;
    JLabel l1, l2, l3;
    Timer timer;
    int flag = 1, flag2 = 1;
    
    ClockDisplay() {
        d = getContentPane();
        d.setLayout(new BoxLayout(d,BoxLayout.Y_AXIS));
         c = new JPanel();
         c1 = new JPanel();
         c2 = new JPanel();
         c3 = new JPanel();
         c4 = new JPanel();
        c.setLayout(new FlowLayout());
        c1.setLayout(new FlowLayout());
        c2.setLayout(new FlowLayout());
        l1 = new JLabel("Clock Display");
        l1.setFont(new Font("Serif",Font.PLAIN,20));
        l2 = new JLabel("Timer Display");
        l2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        l3 = new JLabel("Stopwatch");
        l3.setFont(new Font("Cambria Math",Font.PLAIN,20));
        b1 = new JButton("Clock");
        b1.setPreferredSize(new Dimension(100 ,50));
        b2 = new JButton("Timer");
        b2.setPreferredSize(new Dimension(100 ,50));
        b3 = new JButton("StartWatch");
        b3.setPreferredSize(new Dimension(100 ,50));
        b4 = new JButton("StopWatch");
        b4.setPreferredSize(new Dimension(100 ,50));
        b5 = new JButton("Continue");
        b5.setPreferredSize(new Dimension(100 ,50));
        b6 = new JButton("Reset");
        b6.setPreferredSize(new Dimension(100 ,50));
        t1 = new JTextField("Enter no. of seconds");
        //first container
        c4.add(l1);
        c.add(b1);
        //second container
        c1.add(l2);
        c1.add(t1);
        c1.add(b2);
        // 3rd container
        c3.add(l3);
        c2.add(b3);
        c2.add(b4);
        c2.add(b5);
        c2.add(b6);
        d.add(c1);
        d.add(c4);
        d.add(c);
        d.add(c3);
        d.add(c2);
        // buttons for action
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
        JFrame.setDefaultLookAndFeelDecorated(true);
        cd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cd.setBounds(200, 200, 400,500);
        cd.setVisible(true);
        cd.setTitle("Clock Application");
    }
}
