package dao;

import model.User;

import java.util.List;

public interface UserDao {

    User getUserById(int id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
