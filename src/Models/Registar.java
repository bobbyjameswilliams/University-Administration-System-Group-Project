package Models;

public class Registar extends Employee {

    public Registar (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }

    public void addStudent(){

    }

    public void removeStudent(){

    }

    public void makeModuleCompulsory(){

    }

    // if a student has met all their credit requirements
    public boolean studentMetRequirement(){
        return false;
    }

}
