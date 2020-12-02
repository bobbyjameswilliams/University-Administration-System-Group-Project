package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            Statement stmt = con.createStatement();
            String query = "UPDATE" + this.getTableName() + "SET "+columnName+" = '" + value + "' WHERE "+this.getPrimaryColumn()+ "= " +
                    "'" + this.getCode() + "';";
            System.out.println(query);
            stmt.execute(query);
        } catch (Exception ex){

        }
    };

    public default boolean exists(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM "+this.getTableName()+" WHERE "+this.getPrimaryColumn()+"= '" + this.getCode() + "' ;";
            ResultSet rs = stmt.executeQuery(query);
            return rs.isBeforeFirst();
        } catch (Exception ex){

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
