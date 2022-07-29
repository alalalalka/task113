package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 20);
        userService.saveUser("Vasiliy", "Vasilev", (byte) 27);
        userService.saveUser("Mariya", "Nosova", (byte) 36);
        userService.saveUser("Konstantin", "Aronov", (byte) 49);

        userService.getAllUsers();

        userService.createUsersTable();

        userService.dropUsersTable();

    }
}
