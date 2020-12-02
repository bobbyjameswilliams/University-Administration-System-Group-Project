package Models.DatabaseBehaviours;

import Models.UserAccounts.*;

public class UserManipulator {

    public static void remove(String toRemove,String tableName, String primaryKey){
        String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = '"+toRemove+"';";
        DBController.executeCommand(query);
    }

    public static void addUser(User user) {
        DBController.executeCommand("INSERT INTO User VALUES ('"+user.getUserDetails()+"');");
    }

}
