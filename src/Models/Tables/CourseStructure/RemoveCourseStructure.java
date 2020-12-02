package Models.Tables.CourseStructure;

import Views.Admin.AdminWelcomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveCourseStructure implements ActionListener {

    private AdminWelcomeScreen welcomeScreen;
    private CourseStructureTableModel tableModel;
    private JTable table;

    public <T extends CourseStructureTableModel> RemoveCourseStructure(AdminWelcomeScreen welcomeScreen,JTable table, T tableModel) {
        this.welcomeScreen = welcomeScreen;
        this.table = table;
        this.tableModel = tableModel;
    }

    public <T extends CourseStructureTableModel> void updateTableModel(T t){
        this.tableModel = t;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int[] rows = table.getSelectedRows();
        tableModel.removeRow(rows);
        welcomeScreen.update();
    }

}
