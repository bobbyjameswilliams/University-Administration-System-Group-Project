package Views.Registrar;

import javax.swing.*;
import java.awt.*;

public class RegistrarWelcomeScreen extends javax.swing.JFrame {

    private  JLabel welcome;
    private Container c;

    public RegistrarWelcomeScreen(){

        super("Registrar page");

        c = getContentPane();
        c.setLayout(new FlowLayout());

        welcome= new JLabel("You are logged in as Registrar");

        c.add(welcome);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(180, 200);
    }

    public static void main(String args[]) {

        RegistrarWelcomeScreen registrar = new RegistrarWelcomeScreen();
    }

}

