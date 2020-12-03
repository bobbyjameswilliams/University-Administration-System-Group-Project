package Views.Registrar;

import Controllers.Registrar.InspectRegistrationController;
import Models.Tables.Registrar.InspectRegTableModel;
import Models.UserAccounts.Student.*;;
import Views.WelcomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class InspectRegistration extends WelcomeScreen {
    private JPanel mainPanel;
    private JTable studentModulesTable;
    private JButton backButt;
    private JComboBox optionalModulesCombo;
    private JButton submitButt;
    private JPanel buttonPanel;
    private JLabel moduleCreditsLabel;
    private JLabel addModuleLabel;
    private JLabel studentInfoLabel;
    private JTextField moduleCreditsTakenTxtField;
    private JButton progressStudentToNextButton;
    private JProgressBar progressBar1;
    private JButton retakeLevelButton;
    public JLabel errLabel;
    private JButton removeSelectedButton;
    private final Student student;
    private InspectRegistrationController controller;
    private InspectRegTableModel inspectRegModel;

    public InspectRegistration(Student student, InspectRegistrationController controller) {
        super();
        this.controller = controller;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.student = student;
        this.pack();

        //sets the students info label
        studentInfoLabel.setText("Showing Registration for " + student.getForename() + " " + student.getSurname() +
                " Reg: " + student.getRegNumber() + " Year: " + student.getLevelOfStudy());
        //sets the combobox selections for modules
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel(controller.dataForModuleCombo().toArray());
        optionalModulesCombo.setModel(comboModel);

        retakeLevelButton.addActionListener(e -> {
            this.controller.retake(student);
            this.update();
        });

        progressStudentToNextButton.addActionListener(e -> {
            this.controller.progressStudent(student);
            this.update();
        });


        submitButt.addActionListener(e -> {
            controller.assignOptionalModule(optionalModulesCombo.getSelectedItem().toString(), student);
            this.update();
        });

        removeSelectedButton.addActionListener(e ->{
            //TODO: Do not allow core modules to be removed
            controller.removeOptionalModule(this.inspectRegModel.getRow(studentModulesTable.getSelectedRow()), this.student);
            this.update();
        });
        //Last in statement
        this.update();
    }

    public void update(){
        this.inspectRegModel = new InspectRegTableModel(student);
        studentModulesTable.setModel(inspectRegModel);
        int creditsTaken = student.getCreditsTaken();
        int creditsNeeded = student.getCreditRequirements();
        progressBar1.setMaximum(creditsNeeded);
        moduleCreditsTakenTxtField.setText(Integer.toString(creditsTaken));
        progressBar1.setValue(student.getCreditsTaken());
    }

    //for includes sample data for testing
    public static void main(String args[]){
        //Student student = new Student("Lembrei", "Bobby","Williams","bwilliams4@sheffield.ac.uk",12345,"COMU01","ONE");
        //JFrame frame = new InspectRegistration(student);
        //frame.setVisible(true);
    }
}




