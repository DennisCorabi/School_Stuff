package com.example.springprog.Service;

import com.example.springprog.DataAccess.UserDAO;
import com.example.springprog.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.Vector;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO=userDAO;
    }

    public Vector<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void insertUser(User newuser){
        userDAO.insertUser(newuser);
    }

    public User getUserById(UUID id){
        return userDAO.getUserById(id);
    }

    public void changeUserNameById(UUID id, String name){
        userDAO.changeUserNameById(id,name);
    }

    public void deleteUserById(UUID id){
        userDAO.deleteUserById(id);
    }


}
