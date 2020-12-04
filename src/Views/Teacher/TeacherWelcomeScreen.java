package Views.Teacher;

import Controllers.Teacher.TeacherWelcomeScreenController;
import Models.Tables.Teacher.GraduateTableModel;
import Models.Tables.Teacher.TeachesModuleTableModel;
import Models.UserAccounts.Teacher;
import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherWelcomeScreen extends WelcomeScreen {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable graduatesTable;
    private JTable assStudentsTable;
    private JButton logOutButton;
    private JLabel welcomeLabel;
    private JPanel assStudentsPane;
    private JScrollPane assStudentsTableScroll;
    private JPanel assStudentsActButtPane;
    private JButton stdtApplyButt;
    private JPanel graduatePane;
    private JScrollPane assModulesTableScroll;
    private JButton studentBreakdownButt;
    private JButton refreshButt;
    private Teacher teacher;
    private TeacherWelcomeScreenController controller;
    private TeachesModuleTableModel studentModel;
    private GraduateTableModel gradModel;

    public TeacherWelcomeScreen(Teacher teacher,TeacherWelcomeScreenController controller){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.teacher = teacher;
        this.pack();
        this.controller = controller;

        studentBreakdownButt.addActionListener(e ->{
            int selRowIndex = assStudentsTable.getSelectedRow();
            //Ensures that there is a row selected
            if (selRowIndex > -1) {
                controller.inspectStudentRegistration(studentModel.getRow(selRowIndex));
            }
            this.update();
        });

        this.update();
        //runs method that updates the labels.
        displayWelcomeLabel();
        refreshButt.addActionListener(e ->{this.update();
        });
    }

    private void displayWelcomeLabel() {
        welcomeLabel.setText("Welcome "+this.teacher.getForename()+", Logged in as Teacher ");
    }

    private void update(){
        this.studentModel = new TeachesModuleTableModel(this.teacher);
        assStudentsTable.setModel(studentModel);
        this.gradModel = new GraduateTableModel();
        graduatesTable.setModel(gradModel);
    }
}
