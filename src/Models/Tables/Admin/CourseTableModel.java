package Models.Tables.Admin;

import Models.CourseStructure.Degree;
import Models.CourseStructure.Department;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CourseTableModel extends AbstractTableModel {

    private final List<Degree> courses;

    private final String[] columnNames = new String[] {"Degree Code","Course Name","Length of Study", "Year in industry"};
    private final Class[] columnClass = new Class[] {String.class,String.class, int.class, boolean.class};

    public CourseTableModel() {this.courses = Degree.getAllDegrees();}

    @Override
    public String getColumnName(int column) {return columnNames[column];}

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
        return courses.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Degree row =  courses.get(rowIndex);
        switch (columnIndex){
            case 0:
                return row.getDegreeCode();
            case 1:
                return row.getCourseName();
            case 2:
                return row.getLengthOfStudy();
            case 3:
                return row.getYearInIndustry();
            default:
                return null;
        }
   }

   @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
   {

   }

}
