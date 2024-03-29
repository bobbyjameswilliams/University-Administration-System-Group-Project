package Views.Registrar;

import Controllers.Registrar.RegistrarWelcomeScreenController;
import Models.Tables.Registrar.RegistrarTableModel;
import Models.UserAccounts.Registar;
import Views.WelcomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private RegistrarWelcomeScreenController controller;

    public RegistrarWelcomeScreen(Registar registrar, RegistrarWelcomeScreenController controller){
        super();
        this.controller = controller;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.registrar = registrar;
        this.pack();

        //Sets up the main table
        RegistrarTableModel viewStntTableModel = new RegistrarTableModel();
        viewStntTable.setModel(viewStntTableModel);


        inspectRegistrationButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRowIndex = viewStntTable.getSelectedRow();
                if (selRowIndex > -1) {
                    controller.inspectStudentRegistration(viewStntTableModel.getRow(selRowIndex));
                }
            }
        });
    }

    public static void main(String args[]){
        //Registar registrar = new Registar();
        //JFrame frame = new RegistrarWelcomeScreen(registrar);
        //frame.setVisible(true);
    }
}
