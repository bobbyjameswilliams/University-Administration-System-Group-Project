package Controllers.LogIn;


import Models.Authentication.Login;
import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;
import Models.UserAccounts.*;
import Views.Admin.AdminWelcomeScreen;
import Views.LogIn;
import Views.Student.StudentWelcomeScreen;
import Views.Teacher.TeacherWelcomeScreen;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.NoSuchElementException;

/**
 * Action listeners are added in login class and methods are called here
 * an instance of this is created in login
 *
 */
public class LogInController {

    private LogIn loginFrame;

    public LogInController() {
        this.loginFrame = new LogIn("University Management System",this);
        loginFrame.setVisible(true);
    }

    public void loginButtonPress(String username, String password){
        if (Login.loginAuthenticated(username,password)){
            // if password matched instantiate new view
            JFrame frame = this.getUserFrame(username);
            frame.setVisible(true);
            loginFrame.dispose();
        }
    }


    public JFrame getUserFrame(String username){
        UserAccountBuilder builder = new UserAccountBuilder(username);
        try {
            Student student = builder.studentBuilder();
            JFrame frame = new StudentWelcomeScreen(student,new Object[]{"Placeholder","for","grades"});
            return frame;
        } catch (NoSuchElementException ex){
            switch(builder.getEmployeeRole()){
                case ADMIN:
                    Administrator administrator = builder.employeeBuilder(new Administrator());
                    return new AdminWelcomeScreen(administrator,new Object[]{"User","Columns"}, new Object[]{"Modules","Columns"},
                            new Object[]{"Department","Columns"},new Object[]{"Courses","Columns"});
                case TEACHER:
                    Teacher teacher = builder.employeeBuilder(new Teacher());
                    return new TeacherWelcomeScreen( teacher,new Object[]{"Module","Columns"}, new Object[]{"Student","Columns"});
                case REGISTRAR:
                    builder.employeeBuilder(new Registar());
                    return null;
                default:
                    return null;
            }
        }
    }

    public static void main(String[] args){
        LogInController logInController = new LogInController();
    }

}
