package Views.Student;

import Models.Tables.Student.StudentModuleTable;
import Models.UserAccounts.Student;
import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentWelcomeScreen extends WelcomeScreen {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JScrollPane modulesPane;
    private JScrollPane gradesPane;
    private JTable gradesTable;
    private JTable modulesTable;
    private JLabel welcomeLabel;
    private JLabel personalTutorLabel;
    private JButton logOutButt;
    private Student studentObj;

    /**
     *
     * @param gradeColumns - Columns for the grades table
     */
    public StudentWelcomeScreen(Object gradeColumns[]) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //TODO: interface the moduleColumns and gradeColumns. Need to discuss with callum and salva.
        //instantiating table on model tab
        StudentModuleTable moduleModel = new StudentModuleTable(new Student("test","test","test","test",12345,"ENG040",4));
        modulesTable.setModel(moduleModel);
        //instantiating table on grades tab
        DefaultTableModel gradeModel = new DefaultTableModel(gradeColumns, 0);
        gradesTable.setModel(gradeModel);
        //Instantiates student object using the login details
        studentObj = new Student("Lembrei","Bobby","Williams","",12345,null,1);
        //runs methods that update the welcome labels
        displayTutorLabel();
        displayWelcomeLabel();
    }


    public static void main(String[] args) {
        //is here to allow the form to be displayed without external call
        JFrame frame = new Views.Student.StudentWelcomeScreen(new Object[]{"Placeholder","for","grades"});
        frame.setVisible(true);
    }

    private void displayWelcomeLabel() {

        welcomeLabel.setText("Welcome "+ studentObj.getForename() + " Logged in as Student ");
    }

    private void displayTutorLabel() {
        try {
            List<String> tutorDetails = studentObj.getPersonalTutor();
            System.out.print(tutorDetails);
            personalTutorLabel.setText("PERSONAL TUTOR NAME: " + tutorDetails.get(0) +
                     " " +tutorDetails.get(1) + " EMAIL: " + tutorDetails.get(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
