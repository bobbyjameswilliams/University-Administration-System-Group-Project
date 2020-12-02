package Models.Tables.Admin;

import Models.CourseStructure.CourseStructure;
import Models.CourseStructure.Department;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DepartmentsTableModel extends CourseStructureTableModel {

    public DepartmentsTableModel(Department department){super(department);}

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        CourseStructure row = this.courseStructureList.get(rowIndex);
        if(columnIndex==1) {
            row.update("departmentName",(String) value);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 1;
    }

}
