package com.bsptechs.controller;

import com.bsptechs.beans.UserFormDto;
import com.bsptechs.entities.User;
import com.bsptechs.service.inter.UserServiceInter;
import com.bsptechs.util.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserServiceInter usi;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public String sign(Map<String, Object> model, @ModelAttribute("userForm") UserFormDto userForm) {
        ArrayList<User> list = usi.findAll();
        model.put("users", list);
        return HtmlPage.pageUsers;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("userForm") UserFormDto userForm,
            @RequestParam String action) {
        User user = new User(userForm.getId());
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                usi.save(user);
            } else if (action.equalsIgnoreCase("delete")) {
                usi.deleteById(userForm.getId());
            } else if (action.equalsIgnoreCase("update")) {
                usi.save(user);
            }
        }


        return "redirect: /" + HtmlPage.pageUsers;
    }

    @RequestMapping(path = "signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") UserFormDto userForm) {
        User user = new User();
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        usi.save(user);
        return "redirect: /" + HtmlPage.pageSign;
    }

}
