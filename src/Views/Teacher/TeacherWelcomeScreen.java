package Views.Teacher;

import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Dimension2D;
//TODO: Replace the tabbed pane with a jpane so that the tabs can have a scroll pane and also pane at buttom for butts
public class TeacherWelcomeScreen extends WelcomeScreen {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable assModulesTable;
    private JTable assStudentsTable;
    private JButton logOutButton;
    private JLabel welcomeLabel;
    private JPanel assStudentsPane;
    private JScrollPane assStudentsTableScroll;
    private JPanel assStudentsActButtPane;
    private JButton stdtApplyButt;
    private JPanel assModulesPane;
    private JScrollPane assModulesTableScroll;
    private JPanel assignedModulesActButtPane;
    private JButton viewCohortButt;
    private JButton studentBreakdownButt;

    /**
     *
     * @param moduleColumns Columns for the module table (as an object Object[])
     * @param studentColumns Columns for the student table (as an object Object [])
     */
    public TeacherWelcomeScreen(Object moduleColumns[], Object studentColumns[]){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //TODO: interface the moduleColumns and studentColumns. Need to discuss with callum and salva
        //instantiating table on modules
        DefaultTableModel moduleModel = new DefaultTableModel(moduleColumns, 60);
        assModulesTable.setModel(moduleModel);
        //instantiating table on students tab
        DefaultTableModel studentModel = new DefaultTableModel(studentColumns, 5);
        assStudentsTable.setModel(studentModel);

        //runs method that updates the labels.
        displayWelcomeLabel();
    }

    public static void main(String args[]){
        JFrame frame = new Views.Teacher.TeacherWelcomeScreen
                ( new Object[]{"Module","Columns"}, new Object[]{"Student","Columns"});
        frame.setVisible(true);
    }

    private void displayWelcomeLabel() {
        //TODO: add functionality that displays the users name and welcomes them
        welcomeLabel.setText("Welcome (username), Logged in as Teacher ");
    }
}