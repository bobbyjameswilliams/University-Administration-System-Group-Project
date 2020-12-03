package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CourseStructure {

    final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
    final String user = "team045" ;
    final String password = "5e15b333";

    public void add();

    public void remove();

    public default void update(String columnName,String value){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("UPDATE" + this.getTableName()+"\n" +
                                                                "SET "+columnName+" =? \n" +
                                                                "WHERE "+this.getPrimaryColumn()+"=? ;");
            pstmt.setString(1,value);
            pstmt.setString(2,this.getCode());

            int count = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public default boolean exists(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "+this.getTableName()+"\n" +
                                                                "WHERE "+this.getPrimaryColumn()+"=? ;");
            pstmt.setString(1,this.getCode());


            ResultSet rs = pstmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public String getTableName();

    public String getCode();

    public String getPrimaryColumn();

    public <T extends CourseStructure> List<T> getAll();

    public ArrayList<Object> getVariables();

    //Used for columns for Table Model
    public String[] getVariableNames();

    public Class[] getVariableClass();

}
