package Views.Registrar;

import Controllers.Registrar.RegistrarWelcomeScreenController;
import Models.CourseStructure.LevelOfStudy;
import Models.Tables.Registrar.RegistrarTableModel;
import Models.UserAccounts.Registar;
import Views.WelcomeScreen;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

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

        //Sets the model for the ComboBoxes
        DefaultComboBoxModel courseComboModel = new DefaultComboBoxModel(registrar.getAllCourses());
        sntCourseCombo.setModel(courseComboModel);

        DefaultComboBoxModel levelComboModel = new DefaultComboBoxModel(LevelOfStudy.getAllLevelsOfStudies());
        periodOfStudyCombo.setModel(levelComboModel);


        inspectRegistrationButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRowIndex = viewStntTable.getSelectedRow();
                //Ensures that there is a row selected
                if (selRowIndex > -1) {
                    controller.inspectStudentRegistration(viewStntTableModel.getRow(selRowIndex));
                }
            }
        });

        applyStudentButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ensures that there is a row selected
                int selRowIndex = viewStntTable.getSelectedRow();
                if (selRowIndex > -1){
                    controller.assignStudent(viewStntTableModel.getRow(selRowIndex),
                            sntCourseCombo.getSelectedItem().toString(),
                            periodOfStudyCombo.getSelectedItem().toString());
                };
            }
        });
        delStntButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRowIndex = viewStntTable.getSelectedRow();
                if (selRowIndex > -1){
                    controller.unassignStudent(viewStntTableModel.getRow(selRowIndex));
                };
            }
        });
    }

    public static void main(String args[]){
        //Registar registrar = new Registar();
        //JFrame frame = new RegistrarWelcomeScreen(registrar);
        //frame.setVisible(true);
    }
}
