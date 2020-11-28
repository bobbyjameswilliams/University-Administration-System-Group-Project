package Models.Authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    public void hashTest(){
        byte[] salt = {'B','@',2,0,'d','e','e','a',7,'f'};
        Password password = new Password("test",salt);
        String passwordHashed = "013ba078f38e45445aa75781fc3c73ae2fff2a2f0ed9b19a49e4c445bf47865fde91838a4556d556db5b29e2a0218a0c";
        assertEquals(passwordHashed,password.get_SHA_384_SecurePassword());
    }

}