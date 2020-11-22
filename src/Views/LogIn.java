package Views;

import javax.swing.*;

public class LogIn extends JFrame {
        private JPanel mainPanel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JComboBox privellageCombo;
    private JButton logInButt;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    public LogIn(String title) {
            super(title);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(mainPanel);
            this.pack();

        }


        public static void main(String[] args) {
            JFrame frame = new LogIn("University Admin System");
            frame.setVisible(true);
        }
    }

