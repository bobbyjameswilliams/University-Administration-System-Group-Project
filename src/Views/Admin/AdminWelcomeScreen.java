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
    private JComboBox comboBox1;
    private JLabel editUserLbl;
    private JButton assignPrivellageButt;
    private JLabel newUserLabel;
    private JTextField textField1;
    private JLabel userNameLabel;
    private JLabel forenameLbl;
    private JButton applyNewUserButt;
    private JButton removeSelectedButt;
    private JCheckBox yearInIndustryCheckBox;
    private JTable table1;
    private JButton addAdditonalDeptButton;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JTable table2;
    private JComboBox comboBox3;
    private JButton addRelationButton;
    private JComboBox comboBox4;
    private JTable usersTable;
    private JTable modulesTable;
    private JTable departmentsTable;
    private JTable coursesTable;
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
