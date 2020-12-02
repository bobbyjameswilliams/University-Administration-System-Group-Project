package Models.Tables.CourseStructure;

import Models.CourseStructure.CourseStructure;
import Models.CourseStructure.Department;

import java.util.ArrayList;
import java.util.List;

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
