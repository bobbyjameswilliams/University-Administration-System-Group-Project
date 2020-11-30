package Views.Admin;

import Models.UserAccounts.Administrator;
import Views.WelcomeScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminWelcomeScreen extends WelcomeScreen {
    private JPanel mainPanel;
    private JButton logOutButt;
    private JLabel welcomeLabel;
    private JTabbedPane tabbedPane1;
    private JPanel usersPane;
    private JPanel modulesPane;
    private JPanel departmentPane;
    private JPanel coursePane;
    private JScrollPane usersTableScrollPane;
    private JScrollPane modulesTablesScrollPane;
    private JScrollPane departmentsTableScrollPane;
    private JScrollPane coursesTableScrollPane;
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
     DefaultTableModel usersModel = new DefaultTableModel(usersColumns, 60);
     usersTable.setModel(usersModel);

     DefaultTableModel modulesModel = new DefaultTableModel(modulesColumns, 60);
     modulesTable.setModel(modulesModel);

     DefaultTableModel departmentsModel = new DefaultTableModel(departmentColumns, 60);
     departmentsTable.setModel(departmentsModel);

     DefaultTableModel coursesModel = new DefaultTableModel(coursesColumns, 60);
     coursesTable.setModel(coursesModel);
    }


}
