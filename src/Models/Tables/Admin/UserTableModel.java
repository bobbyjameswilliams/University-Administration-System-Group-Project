package Models.Tables.Admin;

import Models.CourseStructure.CourseStructure;
import Models.Tables.StudentGrade;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserTableModel extends AbstractTableModel{

    private final List<UserTableRow> users;

    private String[] columnNames = new String[]{"Reg Number","Employee Number","Username","Forename","Surname","Email","User Type"};
    private final Class[] columnClass = new Class[]{Integer.class,Integer.class,String.class,String.class,String.class,String.class,String.class};

    public UserTableModel(){
        this.users = UserTableRow.getAllUserTableRow();
    }

    // @Override
    public String getColumnName(int column) {
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
        return users.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        UserTableRow row = users.get(rowIndex);
        switch (columnIndex){
            case 0:
                if (row.getRegNumber()==0){
                    return "N/A";
                }
                return row.getRegNumber();
            case 1:
                if (row.getEmployeeNumber()==0){
                    return "N/A";
                }
                return row.getEmployeeNumber();
            case 2:
                return row.getUsername();
            case 3:
                return row.getForename();
            case 4:
                return row.getSurname();
            case 5:
                return row.getEmail();
            case 6:
                return row.getUserType().toString();
            default:
                return null;
        }
    }

    public void removeRow(int[] rowIndex) {
        List<UserTableRow> userTableRowsToRemove = new ArrayList<>();
        for (int i: rowIndex){
            userTableRowsToRemove.add(this.users.get(i));
        }
        // we couldn't put this in the for loop above because the index would be wrong after first delete
        List<Integer> rowIndexList  = Arrays.stream( rowIndex ).boxed().collect( Collectors.toList() );
        Collections.reverse(rowIndexList);
        for (Integer i : rowIndexList){
            this.users.remove(i);
        }
        // Remove elements from Database
        for (UserTableRow userTableRow: userTableRowsToRemove){
            userTableRow.remove();
        }
    }

}
