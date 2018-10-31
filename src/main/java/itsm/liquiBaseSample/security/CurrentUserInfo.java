package itsm.liquiBaseSample.security;

public class CurrentUserInfo {
    private static final ThreadLocal<Integer> currentUserId = new ThreadLocal<>();

    public static Integer get() {
        return  currentUserId.get();
    }

    public static void set(Integer userId) {
        currentUserId.set(userId);
    }

    public static void unset() {
        currentUserId.remove();
    }


}
