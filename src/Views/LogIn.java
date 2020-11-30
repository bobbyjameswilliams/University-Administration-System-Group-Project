package Views;

import Controllers.LogIn.LogInController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame {

    private JPanel mainPanel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JComboBox privellageCombo;
    private JButton logInButt;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private LogInController loginController;

    public LogIn(String title,LogInController controller) {
            super(title);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(mainPanel);
            this.pack();
            loginController = controller;
        //Submits the data in the fields. see LogInController
        logInButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.loginButtonPress(userNameField.getText(),passwordField.getText());
            }
        });
    }


    public static void main(String[] args) {
        //is here to allow the form to be displayed without external call
        //JFrame frame = new LogIn("University Admin System");
        //frame.setVisible(true);
    }

}

