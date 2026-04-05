package gui;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Hospital Billing System");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(500, 500);

        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("img_1.png"));
        jFrame.setIconImage(icon.getImage());
        jFrame.getContentPane().setBackground(Color.decode("#0049A3"));
        jFrame.setVisible(true);
        
    }
}