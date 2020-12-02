package Views;

import Controllers.LogIn.LogInController;

import javax.swing.*;
import java.awt.*;


public class LogIn extends JFrame {

    private JPanel mainPanel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton logInButt;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel statusLabel;
    private LogInController loginController;

    public LogIn(String title,LogInController controller) {
            super(title);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(mainPanel);
            //Setting size constraints
            super.setMinimumSize(new Dimension(400,175));
            super.setResizable(false);
            super.revalidate();
            this.setLocationRelativeTo(null);
            this.getRootPane().setDefaultButton(logInButt);
            this.pack();
            loginController = controller;
        //Submits the data in the fields. see LogInController
        logInButt.addActionListener(e -> loginController.loginButtonPress(userNameField.getText(),passwordField.getPassword()));
    }

    public void incorrectPassword(){
        statusLabel.setForeground(Color.red);
        statusLabel.setVisible(true);
    }

    public static void main(String[] args) {
        //is here to allow the form to be displayed without external call
        //JFrame frame = new LogIn("University Admin System");
        //frame.setVisible(true);
    }

}

