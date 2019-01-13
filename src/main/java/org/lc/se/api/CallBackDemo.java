package org.lc.se.api;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CallBackDemo {

    public static void main(String[] args) {
        ActionListener al = new MyActionListener();
        Timer timer = new Timer(10000, al);
        timer.start();
    }
}
