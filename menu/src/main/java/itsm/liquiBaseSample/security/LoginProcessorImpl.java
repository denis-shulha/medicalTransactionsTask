package itsm.liquiBaseSample.security;

import java.io.Console;
import java.util.Scanner;

public class LoginProcessorImpl  implements LoginProcessor {

    private static final Scanner scanner = new Scanner(System.in);
    private Console console = System.console();

    private LoginService loginService;
    private int maxLoginAttempts;

    public LoginProcessorImpl(LoginService loginService,
                              int maxLoginAttempts) {
        this.loginService = loginService;
        this.maxLoginAttempts = maxLoginAttempts;
    }

    public boolean startLoginLoop() {
        int currentAttempt = 0;
        while(currentAttempt < maxLoginAttempts) {
            currentAttempt++;
            System.out.println("enter login: ");
            String login = scanner.next();
            System.out.println("enter password:");
            String password = (console != null)
                    ? new String(console.readPassword())
                    : scanner.next();
            if (loginService.doLogin(login,password))
                return true;
            else
                System.out.println("login failed");
        }
        return false;
    }
}
