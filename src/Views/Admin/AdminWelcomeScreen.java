package Views.Admin;


import Models.Tables.Admin.CourseTableModel;

import Models.CourseStructure.Department;
import Models.CourseStructure.UniModule;

import Models.Tables.Admin.DepartmentsTableModel;
import Models.Tables.Admin.ModulesTableModel;
import Models.Tables.Admin.UserTableModel;
import Models.UserAccounts.Administrator;
import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminWelcomeScreen extends WelcomeScreen {
    private JPanel mainPanel;
    private JTabbedPane tabbedPanel;
    private JButton logOutButt;
    private JPanel usersTab;
    private JLabel welcomeLabel;
    private JPanel userTabButtPanel;
    private JComboBox userAccEditPrivilegeCombo;
    private JLabel editUserLbl;
    private JButton assignEditPrivilegeButt;
    private JLabel newUserLabel;
    private JTextField userNameTxtField;
    private JLabel userNameLabel;
    private JLabel userNameLbl;
    private JButton applyNewUserButt;
    private JButton removeSelectedButt;
    private JCheckBox yearInIndustryCheckBox;
    private JTable table1;
    private JButton courseAddAdditionalButton;
    private JComboBox courseAdditionalDeptCombo;
    private JTextField deptCodeTxtField;
    private JTable coreRelationsTable;
    private JComboBox coreModulesDeptCombo;
    private JButton addRelationButton;
    private JComboBox modulePeriodOfStudyRelationCombo;
    private JScrollPane departmentScrollPane;
    private JPanel departmentMethodsScrollPane;
    private JLabel addDeptLbl;
    private JLabel deptNameLbl;
    private JTextField deptNameTxtField;
    private JButton addDeptButt;
    private JButton removeSelectDeptButt;
    private JLabel deptCodeLbl;
    private JScrollPane coursesTableScrollPane;
    private JPanel editCoursesPane;
    private JTable usersTable;
    private JTable modulesTable;
    private JTable departmentsTable;
    private JTable coursesTable;
    private JLabel addCourseLbl;
    private JLabel courseNameLbl;
    private JTextField courseNameTxt;
    private JLabel courseLengthLbl;
    private JTextField courseLengthTxtArea;
    private JLabel courseDegreeCodeLbl;
    private JButton addCourseButt;
    private JButton removeSelectedCourseButt;
    private JLabel courseLeadDeptLbl;
    private JComboBox courseLeadDeptCombo;
    private JTextField degreeCodeTxtField;
    private JScrollPane courseAddDeptScrollPane;
    private JLabel additionalDeptLbl;
    private JScrollPane modulesScrollPane;
    private JPanel modulesAddPane;
    private JTextField moduleNameTxtField;
    private JTextField moduleCreditsTxtField;
    private JComboBox leadDeptComboBox;
    private JScrollPane coreModulesScrollPane;
    private JScrollPane userTableScrollPane;
    private JTextField userForeNameTxtField;
    private JTextField userSurNameTxtField;
    private JTextField userEmailTxtField;
    private JComboBox userPrivilegeCombo;
    private Administrator admin;

    public AdminWelcomeScreen(Administrator admin){
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.admin = admin;
        this.pack();

        //Sets welcome label
        welcomeLabel.setText("Welcome " + admin.getForename() + " " + admin.getSurname() +
                ", Logged in as Administrator");
        //instantiating table
        // DefaultTableModel usersModel = new DefaultTableModel(usersColumns, 60);
        // usersTable.setModel(usersModel);
        ModulesTableModel modulesModel = new ModulesTableModel(new UniModule());
        modulesTable.setModel(modulesModel);

        DepartmentsTableModel departmentsModel = new DepartmentsTableModel(new Department());
        departmentsTable.setModel(departmentsModel);

        CourseTableModel coursesModel = new CourseTableModel();
        coursesTable.setModel(coursesModel);

        UserTableModel userTableModel = new UserTableModel();
        usersTable.setModel(userTableModel);

    }

    public static void main(String args[]){
        Administrator admin = new Administrator();
        JFrame frame = new AdminWelcomeScreen(admin);
        frame.setVisible(true);
    }


}
