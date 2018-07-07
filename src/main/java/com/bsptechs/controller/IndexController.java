/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsptechs.controller;

import com.bsptechs.beans.LoginFormDto;
import com.bsptechs.entities.User;
import com.bsptechs.service.inter.UserServiceInter;
import com.bsptechs.util.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(path = {"index", "/"})
public class IndexController {

    @Autowired
    UserServiceInter usi;

    @RequestMapping(method = RequestMethod.GET)
    public String login(Map<String, Object> model, @ModelAttribute("login") LoginFormDto login) {
        User user = usi.findUserByEmailAndPassword(login.getEmail(), login.getPassword());
        model.put("user", user);
        return HtmlPage.pageIndex;
    }


}