package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(long id);

    public void saveOrUpdate(User user);

    public void deleteUser(long id);
}
