package Controllers.LogIn;


import Models.Authentication.Login;
import Models.UserAccounts.Student;
import Models.UserAccounts.UserAccountBuilder;
import Views.LogIn;
import Views.Student.StudentWelcomeScreen;
import com.mysql.cj.log.Log;

import javax.swing.*;

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
            Student student = UserAccountBuilder.studentBuilder(username);
            JFrame frame = new StudentWelcomeScreen(student,new Object[]{"Placeholder","for","grades"});
            frame.setVisible(true);
            loginFrame.dispose();
        }
    }

    public static void main(String[] args){
        LogInController logInController = new LogInController();
    }

}
