package Dao;

import Model.User;

import java.util.List;

public interface UserDao {
    User getUser(String username);
    boolean checkExitsUsername(String username);
    List<User> getUsers();
    void updateUser(User user);
    User getUser(int id);
    void deleteUser(int id);
}
