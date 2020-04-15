package convolutioncalculator;


import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConvolutionCalculator extends Applet implements ActionListener {

    Button addx;
    Button addh;
    Button reset;
    Button calculate;

    TextField xLocation;
    TextField xValue;
    TextField hLocation;
    TextField hValue;

    int[] x = new int[11];
    int[] h = new int[11];
    int[] conv = new int[21];

    public void init() {
        setSize(1100, 1100);
        setVisible(true);
    }

    public ConvolutionCalculator() {
        setLayout(null);

        xLocation = new TextField("Entry to Location");
        xLocation.setSize(100, 20);
        xLocation.setLocation(80, 300);
        add(xLocation);
        xLocation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xLocation.setText("");
            }
        });

        xValue = new TextField("Entry to Value");
        xValue.setBounds(200, 300, 100, 20);
        add(xValue);
        xValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xValue.setText("");
            }
        });

        addx = new Button("Add to x Graph");
        addx.setSize(100, 30);
        addx.setLocation(80, 350);
        add(addx);
        addx.addActionListener(this);

        reset = new Button("Reset");
        reset.setSize(100, 30);
        reset.setLocation(200, 350);
        reset.addActionListener(this);
        add(reset);

        ////////////////////////////////////////////////
        hLocation = new TextField("Entry to Location");
        hLocation.setBounds(580, 300, 100, 20);
        add(hLocation);
        hLocation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hLocation.setText("");
            }
        });

        hValue = new TextField("Entry to Value");
        hValue.setBounds(700, 300, 100, 20);
        add(hValue);
        hValue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                hValue.setText("");
            }
        });

        addh = new Button("Add to h Graph");
        addh.setSize(100, 30);
        addh.setLocation(580, 350);
        addh.addActionListener(this);
        add(addh);

        calculate = new Button("Calculate");
        calculate.setSize(100, 30);
        calculate.setLocation(700, 350);
        calculate.addActionListener(this);
        add(calculate);

    }

    public void paint(Graphics g) {
        //x[n] graph
        g.drawString("x[n]", 290, 40);
        g.drawLine(80, 250, 520, 250);
        g.drawLine(300, 250, 300, 50); //Center point of graph
        int valueOfAxis = -5;
        int count = 0;
        for (int i = 100; i <= 500; i += 40) {
            g.drawString("" + valueOfAxis, i - 2, 265);
            g.drawLine(i, 250, i, 250 - (x[count] * 10));
            if (valueOfAxis != 0) {
                g.drawString("" + x[count], i - 2, 250 - (x[count] * 10) - 10);
            } else {
                g.drawString("" + x[count], i +5, 250 - (x[count] * 10) - 10);
            }
            valueOfAxis++;
            count++;
        }

        //h[n] graph
        g.drawString("h[n]", 785, 40);
        g.drawLine(580, 250, 1020, 250);
        g.drawLine(800, 250, 800, 50); //Center point of graph
        valueOfAxis = -5;
        count = 0;
        for (int i = 600; i <= 1000; i += 40) {
            g.drawString("" + valueOfAxis, i - 2, 265);
            g.drawLine(i, 250, i, 250 - (h[count] * 10));
            if (valueOfAxis != 0) {
                g.drawString("" + h[count], i - 2, 250 - (h[count] * 10) - 10);
            } else {
                g.drawString("" + h[count], i +5, 250 - (h[count] * 10) - 10);
            }
            valueOfAxis++;
            count++;
        }
        //Convolution graph
        g.drawString("x[n]*h[n-k]", 530, 390);
        g.drawLine(30, 800, 1070, 800);
        g.drawLine(550, 800, 550, 400);
        valueOfAxis = -10;
        count = 0;
        for (int i = 50; i <= 1050; i += 50) {
            g.drawString("" + valueOfAxis, i - 2, 815);
            g.drawLine(i, 800, i, 800 - (conv[count] * 10));
            if (valueOfAxis != 0) {
                g.drawString("" + conv[count], i - 2, 800 - (conv[count] * 10) - 10);
            } else {
                g.drawString("" + conv[count], i +5, 800 - (conv[count] * 10) - 10);
            }
            valueOfAxis++;
            count++;
        }

    }

    public void calculator(int x[], int h[]) {
        int sum = 0;
        int box = 0;

        for (int i = 0; i <= 10; i++) {

            int s = i;
            int u = i;

            for (int j = 0; j <= i; j++) {
                sum = h[j] * x[s];
                box += sum;
                conv[u] = box;
                s--;
            }
            box = 0;

        }

        for (int i = 1; i <= 10; i++) {

            int s = i;
            int u = i + 10;
            for (int j = 10; j >= i; j--) {
                sum = h[j] * x[s];
                box += sum;
                conv[u] = box;
                s++;
            }
            box = 0;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addx) {
            int i = Integer.parseInt(xLocation.getText());
            int a = Integer.parseInt(xValue.getText());
            x[i + 5] = a;
            repaint();
        } else if (e.getSource() == addh) {
            int i = Integer.parseInt(hLocation.getText());
            int a = Integer.parseInt(hValue.getText());
            h[i + 5] = a;
            repaint();
        } else if (e.getSource() == reset) {
            x = new int[11];
            h = new int[11];
            conv = new int[21];
            repaint();
        } else if (e.getSource() == calculate) {
            calculator(x, h);
            repaint();
        }

    }

}
