package Views.Admin;


import Controllers.Admin.AdminController;
import Models.CourseStructure.Degree;
import Models.Tables.CourseStructure.CourseTableModel;

import Models.CourseStructure.Department;
import Models.CourseStructure.UniModule;

import Models.Tables.CourseStructure.DepartmentsTableModel;
import Models.Tables.CourseStructure.ModulesTableModel;
import Models.Tables.Admin.UserTableModel;
import Models.Tables.CourseStructure.RemoveCourseStructure;
import Models.UserAccounts.UserType;
import Views.WelcomeScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel courseDegreeSigLbl;
    private JButton addCourseButt;
    private JButton removeSelectedCourseButt;
    private JLabel courseLeadDeptLbl;
    private JComboBox courseLeadDeptComboBox;
    private JTextField degreeCodeTxtField;
    private JScrollPane courseAddDeptScrollPane;
    private JLabel additionalDeptLbl;
    private JScrollPane modulesScrollPane;
    private JPanel modulesAddPane;
    private JTextField moduleNameTxtField;
    private JTextField moduleCreditsTxtField;
    private JComboBox moduleLeadDeptComboBox;
    private JScrollPane coreModulesScrollPane;
    private JScrollPane userTableScrollPane;
    private JTextField userForeNameTxtField;
    private JTextField userSurNameTxtField;
    private JComboBox<UserType> userPrivilegeCombo;
    private JButton moduleAddButton;
    private JTextField moduleNumberTxtField;
    private JButton removeSelectedModuleButton;
    private JTextField qualificationTxtField;
    private AdminController controller;

    public AdminWelcomeScreen(AdminController controller){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.controller = controller;
        this.pack();

        welcomeLabel.setText("Welcome " + controller.getAdminForename() + " " + controller.getAdminSurname() +
                ", Logged in as Administrator");

        addDeptButt.addActionListener(e -> {
            this.controller.addDepartment(deptCodeTxtField.getText(), deptNameTxtField.getText());
            deptCodeTxtField.setText("");
            deptNameTxtField.setText("");
            this.updateDepartmentPane();
        });

        addCourseButt.addActionListener(e -> {
            this.controller.addCourse((String)courseLeadDeptComboBox.getSelectedItem(),degreeCodeTxtField.getText(),courseNameTxt.getText(),Integer.parseInt(courseLengthTxtArea.getText()),yearInIndustryCheckBox.isSelected(),
            qualificationTxtField.getText());
            this.degreeCodeTxtField.setText("");
            this.courseNameTxt.setText("");
            this.courseLengthTxtArea.setText("");
            this.updateCoursePane();
        });

        applyNewUserButt.addActionListener(e -> {
            this.controller.addUser((UserType)userPrivilegeCombo.getSelectedItem(),userForeNameTxtField.getText(),userSurNameTxtField.getText());
            userForeNameTxtField.setText("");
            userSurNameTxtField.setText("");
            this.updateUserPane();
        });

        moduleAddButton.addActionListener(e -> {
            this.controller.addModule(moduleNumberTxtField.getText(),moduleNameTxtField.getText(),Integer.parseInt(moduleCreditsTxtField.getText()),(String)moduleLeadDeptComboBox.getSelectedItem());
            moduleNumberTxtField.setText("");
            moduleNameTxtField.setText("");
            moduleCreditsTxtField.setText("");
            this.updateModulePane();
        });

        removeSelectDeptButt.addActionListener(new RemoveCourseStructure(this,departmentsTable,new DepartmentsTableModel(new Department())));
        removeSelectedCourseButt.addActionListener(new RemoveCourseStructure(this,coursesTable,new CourseTableModel(new Degree())));
        removeSelectedModuleButton.addActionListener(new RemoveCourseStructure(this,modulesTable,new ModulesTableModel(new UniModule())));

        this.update();
    }

    private void setUserPrivilegeComboBox(){
        DefaultComboBoxModel<UserType> model = new DefaultComboBoxModel(UserType.getAllUserTypes());
        userPrivilegeCombo.setModel(model);
    }

    private void setLeadDeptComboBox() {
        List<Department> departments = new Department().getAll();
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getDepartmentName());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel(departmentNames.toArray());
        moduleLeadDeptComboBox.setModel(model);
        courseLeadDeptComboBox.setModel(model);
    }

    public void update(){
        this.updateModulePane();
        this.updateDepartmentPane();
        this.updateCoursePane();
        this.updateUserPane();
    }

    private void updateModulePane(){
        ModulesTableModel modulesModel = new ModulesTableModel(new UniModule());
        modulesTable.setModel(modulesModel);
        // Update the table model the ActionListenerUses
        RemoveCourseStructure rcs = (RemoveCourseStructure)removeSelectedModuleButton.getActionListeners()[0];
        rcs.updateTableModel(modulesModel);
        this.setLeadDeptComboBox();
    }

    private void updateDepartmentPane(){
        DepartmentsTableModel departmentsModel = new DepartmentsTableModel(new Department());
        departmentsTable.setModel(departmentsModel);
        RemoveCourseStructure rcs = (RemoveCourseStructure)removeSelectDeptButt.getActionListeners()[0];
        rcs.updateTableModel(departmentsModel);
    }

    private void updateCoursePane(){
        CourseTableModel coursesModel = new CourseTableModel(new Degree());
        coursesTable.setModel(coursesModel);
        RemoveCourseStructure rcs = (RemoveCourseStructure)removeSelectedCourseButt.getActionListeners()[0];
        rcs.updateTableModel(coursesModel);
    }

    private void updateUserPane() {
        UserTableModel userTableModel = new UserTableModel();
        usersTable.setModel(userTableModel);
        this.setUserPrivilegeComboBox();
    }

}
