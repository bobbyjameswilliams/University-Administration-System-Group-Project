package Views.Admin;

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
    private JComboBox userAccEditPribellageCombo;
    private JLabel editUserLbl;
    private JButton assignEditPrivellageButt;
    private JLabel newUserLabel;
    private JTextField userNameTxtField;
    private JLabel userNameLabel;
    private JLabel userNameLbl;
    private JButton applyNewUserButt;
    private JButton removeSelectedButt;
    private JCheckBox yearInIndustryCheckBox;
    private JTable table1;
    private JButton addAdditonalDeptButton;
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
    private JComboBox userPrivellageCombo;
    private Administrator admin;

    //TODO: Fix issue where the table doesnt fill the viewport
    public AdminWelcomeScreen(Administrator admin, Object[]usersColumns, Object[]modulesColumns, Object[]departmentColumns, Object[]coursesColumns){
     super();

     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     this.setContentPane(mainPanel);
     this.admin = admin;
     this.pack();

     //TODO: interface. Need to discuss with callum and salva
     //instantiating table
    // DefaultTableModel usersModel = new DefaultTableModel(usersColumns, 60);
    // usersTable.setModel(usersModel);

    // DefaultTableModel modulesModel = new DefaultTableModel(modulesColumns, 60);
    // modulesTable.setModel(modulesModel);

    // DefaultTableModel departmentsModel = new DefaultTableModel(departmentColumns, 60);
    // departmentsTable.setModel(departmentsModel);

     //DefaultTableModel coursesModel = new DefaultTableModel(coursesColumns, 60);
     //coursesTable.setModel(coursesModel);
    }

    public static void main(String args[]){
        Administrator admin = new Administrator();
        JFrame frame = new AdminWelcomeScreen(admin, new Object[]{""},new Object[]{""},new Object[]{""},new Object[]{""});
        frame.setVisible(true);
    }


}
