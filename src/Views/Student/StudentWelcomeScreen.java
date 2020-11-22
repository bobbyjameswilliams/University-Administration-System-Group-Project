package Views.Student;

import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    /**
     *
     * @param moduleColumns - Columns for the modules table
     * @param gradeColumns - Columns for the grades table
     */
    public StudentWelcomeScreen(Object moduleColumns[], Object gradeColumns[]) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //TODO: interface the moduleColumns and gradeColumns. Need to discuss with callum and salva.
        //instantiating table on model tab
        DefaultTableModel moduleModel = new DefaultTableModel(moduleColumns, 0);
        modulesTable.setModel(moduleModel);
        //instantiating table on grades tab
        DefaultTableModel gradeModel = new DefaultTableModel(gradeColumns, 0);
        gradesTable.setModel(gradeModel);

        //runs methods that update the welcome labels
        displayTutorLabel();
        displayWelcomeLabel();
    }


    public static void main(String[] args) {
        //is here to allow the form to be displayed without external call
        JFrame frame = new Views.Student.StudentWelcomeScreen(new Object[]{"Placeholder","for","modules"}, new Object[]{"Placeholder","for","grades"});
        frame.setVisible(true);
    }

    private void displayWelcomeLabel() {
        //TODO: add functionality that displays the users name and welcomes them
        welcomeLabel.setText("Welcome (username), Logged in as Student ");
    }

    private void displayTutorLabel() {
        // TODO: add functionality that displays the users personal tutor
        personalTutorLabel.setText("Your personal tutor is: email:");
    }
}
