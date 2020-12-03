package Models.Tables.Admin;

import Models.Tables.CourseStructure.CourseStructureTableModel;
import Views.Admin.AdminWelcomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSelectedUser implements ActionListener {
    private AdminWelcomeScreen welcomeScreen;
    private UserTableModel tableModel;
    private JTable table;

    public RemoveSelectedUser(AdminWelcomeScreen welcomeScreen,JTable table, UserTableModel tableModel) {
        this.welcomeScreen = welcomeScreen;
        this.table = table;
        this.tableModel = tableModel;
    }

    public void updateTableModel(UserTableModel tableModel){
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int[] rows = table.getSelectedRows();
        tableModel.removeRow(rows);
        welcomeScreen.update();
    }
}
