package Models.DatabaseBehaviours;

import Models.UserAccounts.*;

public class UserManipulator {

    public static void remove(String toRemove,String tableName, String primaryKey){
        DBController.executeCommand("DELETE FROM " + tableName + " where " + primaryKey + " = '"+toRemove+"';");
    }

    public static void addUser(User user) {
        DBController.executeCommand("INSERT INTO User VALUES ('"+user.getUserDetails()+"');");
    }

}
