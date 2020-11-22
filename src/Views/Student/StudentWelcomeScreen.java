package Views.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentWelcomeScreen extends JFrame {


    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JScrollPane modulesPane;
    private JScrollPane gradesPane;
    private JTable gradesTable;
    private JTable modulesTable;
    private JLabel welcomeLabel;
    private JLabel personalTutorLabel;

    public StudentWelcomeScreen(String title, Object moduleColumns[], Object gradeColumns[]) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //instantiating table on model tab
        DefaultTableModel moduleModel = new DefaultTableModel(moduleColumns, 90);
        modulesTable.setModel(moduleModel);
        //instantiating table on grades tab
        DefaultTableModel gradeModel = new DefaultTableModel(gradeColumns, 90);
        gradesTable.setModel(gradeModel);

        //runs methods that update the welcome labels
        displayTutorLabel();
        displayWelcomeLabel();
    }


    public static void main(String[] args) {
        //is here to allow the form to be displayed without external call
        JFrame frame = new Views.Student.StudentWelcomeScreen("University Admin System", new Object[]{"hello"}, new Object[]{"hey"});
        frame.setVisible(true);
    }

    private void displayWelcomeLabel() {
        //TODO: add functionality that displays the users name and welcomes them
        welcomeLabel.setText("Welcome (username), Logged in as Student ");
    }

    private void displayTutorLabel() {
        // TODO: add functionality that displays the users personal tutor
        welcomeLabel.setText("Your personal tutor is: email:");
    }
}
