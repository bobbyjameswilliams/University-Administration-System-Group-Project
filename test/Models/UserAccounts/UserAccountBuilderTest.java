package Models.UserAccounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountBuilderTest {

    @Test
    public void studentBuilderTest(){
        Student student = UserAccountBuilder.studentBuilder(12345);
        Student student1 = new Student("Lembrei","Bobby","Williams","bwilliams4@sheffield.ac.uk",
                12345,null,1);
        assertEquals(student.getEmailAddress(),student1.getEmailAddress());
    }



}