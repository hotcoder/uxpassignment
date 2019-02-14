package com.uxpsystems.assignment.controller;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;
import com.uxpsystems.assignment.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/assignment")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("user_list");
        List<User> userList = userService.getAllUsers();
        model.addObject("userList", userList);
        return model;
    }

    @RequestMapping(value="/addUser", method=RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView model = new ModelAndView();

        User user = new User();
        user.setStatus(StatusEnum.Activated.toString());
        model.addObject("userForm", user);
        model.setViewName("user_form");

        return model;
    }

    @RequestMapping(value="/saveUser", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("articleForm") User user) {
        user.setStatus("Activated");
        userService.saveOrUpdate(user);
        return new ModelAndView("redirect:/assignment/list");
    }

    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/assignment/list");
    }


    @RequestMapping(value="/updateUser/{id}", method=RequestMethod.GET)
    public ModelAndView editUser(@PathVariable long id) {
        ModelAndView model = new ModelAndView();

        User user = userService.getUserById(id);
        model.addObject("userForm", user);
        model.setViewName("user_form");

        return model;
    }
}

