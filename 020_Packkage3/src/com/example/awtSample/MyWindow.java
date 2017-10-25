package com.example.awtSample;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by M4800 on 23-Nov-16.
 */
public class MyWindow extends Frame {
    public MyWindow(String title) {
        super(title);
        setSize(500, 140);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font sanLarge = new Font("SanSerif", Font.BOLD, 18);
        Font sanSmall = new Font("Sanserif", Font.BOLD, 12);
        g.setFont(sanLarge);
        g.drawString("The Complete Java Developer Course", 60, 60);
        g.setFont(sanSmall);
        g.drawString(" by Quan", 60, 100);
    }
}
