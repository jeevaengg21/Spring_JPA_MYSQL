/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;

/**
 *
 * @author Jeeva
 */
import com.example.demo.entities.User;
import com.example.demo.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    // PUBLIC METHODS
    // ------------------------
    /**
     * /create --> Create a new user and save it in the database.
     *
     * @param email User's email
     * @param name User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String fname, String lname) {
        User user = null;
        try {
            user = new User(fname, lname);
            userDao.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /get-by-email --> Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping("/get-by-name")
    @ResponseBody
    public String getByEmail(String fname) {
        String userId;
        try {
            User user = userDao.findByFname(fname);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping("/get")
    @ResponseBody
    public User getAll() {
        String userId;
        try {
            Long l = Long.parseLong("1");
            return userDao.findOne(l);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * /update --> Update the email and the name for the user in the database
     * having the passed id.
     *
     * @param id The id for the user to update.
     * @param email The new email.
     * @param name The new name.
     * @return A string describing if the user is succesfully updated or not.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String fname, String lname) {
        try {
            User user = userDao.findOne(id);
            user.setFname(fname);
            user.setLname(lname);
            userDao.save(user);
        } catch (Exception ex) {
            return "Error updating the user:" + ex.toString();
        }
        return "User succesfully updated!";
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    @Autowired
    private UserDao userDao;

} // class UserController
