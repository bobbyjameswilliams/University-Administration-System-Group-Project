package Views.Registrar;

import Models.Tables.Registrar.InspectRegTableModel;
import Models.UserAccounts.Student;
import Views.WelcomeScreen;

import javax.swing.*;

public class InspectRegistration extends WelcomeScreen {
    private JPanel mainPanel;
    private JTable studentModulesTable;
    private JButton backButt;
    private JComboBox comboBox1;
    private JButton submitButt;
    private JPanel buttonPanel;
    private JLabel moduleCreditsLabel;
    private JLabel addModuleLabel;
    private JLabel studentInfoLabel;
    private final Student student;

    public InspectRegistration(Student student) {
        super();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.student = student;
        this.pack();

        //sets the students info label
        studentInfoLabel.setText("Showing Registration for " + student.getForename() + " " + student.getSurname() +
                " Reg: " + student.getRegNumber());

        //instantiates the table model
        InspectRegTableModel inspectRegModel = new InspectRegTableModel(student);
        studentModulesTable.setModel(inspectRegModel);
    }

    //for includes sample data for testing
    public static void main(String args[]){
        Student student = new Student("Lembrei", "Bobby","Williams","bwilliams4@sheffield.ac.uk",12345,"COMU01","ONE");
        JFrame frame = new InspectRegistration(student);
        frame.setVisible(true);
    }
}




