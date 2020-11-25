package Models.Tables;

import Models.UserAccounts.Employee;
import Models.UserAccounts.Student;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TeachesModuleTableModel extends AbstractTableModel {

    private final List<Student> studentList;

    private final String[] columnNames = new String[] {"Reg Code","Degree Code","Level of Study"};
    private final Class[] columnClass = new Class[] {String.class,String.class,Integer.class};

    public TeachesModuleTableModel(){
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(generateStudent());
        studentList.add(generateStudent());
        studentList.add(generateStudent());
        this.studentList = studentList;
    }

    public TeachesModuleTableModel(List<Student> studentList)
    {
        this.studentList = studentList;
    }

    private Student generateStudent() {
        return new Student("test","test","test","test",12345,"ENG040",4);
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
        return this.studentList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Student row = studentList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getRegNumber();
        }
        else if(1 == columnIndex) {
            return row.getDegreeCode();
        }
        else if(2 == columnIndex) {
            return row.getLevelOfStudy();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        Student row = studentList.get(rowIndex);
        if(2 == columnIndex) {
            row.setLevelOfStudy((Integer) value);
            row.updateLevelOfStudy();
        }
    }

    @Override
    public boolean isCellEditable(int rowindex, int columnIndex){
        return (columnIndex >= 2);
    }

}
