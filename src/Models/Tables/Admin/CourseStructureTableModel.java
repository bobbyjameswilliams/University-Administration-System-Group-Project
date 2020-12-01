package Models.Tables.Admin;

import Models.CourseStructure.CourseStructure;
import Models.CourseStructure.Department;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class CourseStructureTableModel extends AbstractTableModel {

    protected List<? extends CourseStructure> courseStructureList = new ArrayList<>();

    protected final String[] columnNames;
    protected final Class[] columnClass;


    public <T extends CourseStructure> CourseStructureTableModel(T t) {
        this.courseStructureList = t.getAll();
        this.columnNames = t.getVariableNames();
        this.columnClass = t.getVariableClass();
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
        return courseStructureList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ArrayList variableList = courseStructureList.get(rowIndex).getVariables();
        return variableList.get(columnIndex);
    }

    // This can be Overridden again
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

}
