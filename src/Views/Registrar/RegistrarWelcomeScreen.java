package Views.Registrar;

import Models.Tables.Registrar.RegistrarTableModel;
import Models.UserAccounts.Registar;
import Views.WelcomeScreen;

import javax.swing.*;

public class RegistrarWelcomeScreen extends WelcomeScreen {
    private JPanel mainPanel;
    private JComboBox periodOfStudyCombo;
    private JComboBox sntCourseCombo;
    private JPanel stntCoursePane;
    private JLabel stntCourseLbl;
    private JPanel periodOfStudyPane;
    private JLabel periodOfStudyLbl;
    private JTable viewStntTable;
    private JPanel viewStntButtPane;
    private JScrollPane viewStntScrollPane;
    private JButton delStntButt;
    private JButton applyStudentButt;
    private JButton inspectRegistrationButt;
    private JLabel welcomeLabel;
    private Registar registrar;
    public RegistrarWelcomeScreen(Registar registrar){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.registrar = registrar;
        this.pack();

        //Sets up the main table
        RegistrarTableModel viewStntTableModel = new RegistrarTableModel();
        viewStntTable.setModel(viewStntTableModel);
    }

    public static void main(String args[]){
        Registar registrar = new Registar();
        JFrame frame = new RegistrarWelcomeScreen(registrar);
        frame.setVisible(true);
    }
}
