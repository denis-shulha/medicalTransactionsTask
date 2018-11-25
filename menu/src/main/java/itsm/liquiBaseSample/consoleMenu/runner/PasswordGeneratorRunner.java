package itsm.liquiBaseSample.consoleMenu.runner;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordGeneratorRunner {



    public static void main(String[] args) {
        String salt = "$2a$10$T9PxbzNPQQktTxz9NesnqO";
        String pw = "User2_pw";
        System.out.println(BCrypt.hashpw(pw,salt));
    }
}
