package itsm.liquiBaseSample.consoleMenu.security;

import itsm.liquiBaseSample.domains.User;

public class CurrentUserInfo {
    private static final ThreadLocal<Integer> currentUserId = new ThreadLocal<>();

    public static User get() {
        User currentUser = new User();
        currentUser.setId(currentUserId.get());
        return  currentUser;
    }

    public static void set(Integer userId) {
        currentUserId.set(userId);
    }

    public static void unset() {
        currentUserId.remove();
    }


}
