package Models.Tables;

import Models.UserAccounts.Employee;
import Models.UserAccounts.Student;
import Models.UserAccounts.Teacher;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TeachesModuleTableModel extends AbstractTableModel {

    private final List<StudentGrade> studentGrades;

    private final String[] columnNames = new String[] {"Reg Number","Forename","Surname","Grade","Resit"};
    private final Class[] columnClass = new Class[] {Integer.class,String.class,String.class,Integer.class,Integer.class};

    public TeachesModuleTableModel(Teacher teacher){
        this.studentGrades = teacher.getGradesOfStudents();
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
                return row.getRegNumber();
            case 1:
                return row.getForename();
            case 2:
                return row.getSurname();
            case 3:
                return row.getGrade();
            case 4:
                return row.getResit();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        StudentGrade row = studentGrades.get(rowIndex);
        if(3 == columnIndex) {
            row.setGrade((Integer) value);
        }
    }

    @Override
    public boolean isCellEditable(int rowindex, int columnIndex){
        return (columnIndex == 3);
    }

}
