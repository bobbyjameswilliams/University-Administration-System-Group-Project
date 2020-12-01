package Models.Tables.Admin;

import Models.CourseStructure.Department;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DepartmentsTableModel extends AbstractTableModel {

    private final List<Department> departments;

    private final String[] columnNames = new String[] {"Department Code","Department Name"};
    private final Class[] columnClass = new Class[] {String.class,String.class};

    public DepartmentsTableModel() {this.departments = Department.getAllDepartments();}

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return departments.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Department row =  departments.get(rowIndex);
        switch (columnIndex){
            case 0:
                return row.getDepartmentCode();
            case 1:
                return row.getDepartmentName();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        Department row = departments.get(rowIndex);
        if(columnIndex==1) {
            row.updateDepartmentName((String) value);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 1;
    }


}
