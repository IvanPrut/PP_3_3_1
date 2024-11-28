package org.firstwebproject.pp_3_3_1.service;

import org.firstwebproject.pp_3_3_1.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    List<User> findAllUsers();
}
