package Models.Tables.Student;

import Models.Tables.StudentGrade;

import javax.swing.table.AbstractTableModel;

import Models.UserAccounts.Employee;
import Models.UserAccounts.Student;
import Models.UserAccounts.Teacher;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentModuleTable extends AbstractTableModel {

    private final List<StudentGrade> studentGrades;

    private final String[] columnNames = new String[] {"Grade","Resit"};
    private final Class[] columnClass = new Class[] {Integer.class,Integer.class};

    public StudentModuleTable(Student student){
        this.studentGrades = student.getModules();
    }

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
        return studentGrades.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        StudentGrade row = studentGrades.get(rowIndex);
        switch (columnIndex){
            case 0:
                return row.getGrade();
            case 1:
                return row.getResit();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowindex, int columnIndex){
        return false;
    }

}

