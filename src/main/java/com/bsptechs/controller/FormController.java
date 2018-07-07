package com.bsptechs.controller;

import com.bsptechs.beans.FormDto;
import com.bsptechs.entities.Form;
import com.bsptechs.service.inter.FormServiceInter;
import com.bsptechs.util.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Anar Salami
 */
@Controller
@RequestMapping("forms")
public class FormController {

    @Autowired
    FormServiceInter fsi;

    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model, @ModelAttribute("forms") FormDto forms) {
        ArrayList<Form> list = fsi.findAll();
        model.put("forms", list);
        return HtmlPage.pageForm;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("forms") FormDto formDto,
            @RequestParam String action) {
        Form form = new Form(formDto.getId());

        form.setName(formDto.getName());
        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                fsi.save(form);
            } else if (action.equalsIgnoreCase("delete")) {
                fsi.deleteById(formDto.getId());
            } else if (action.equalsIgnoreCase("update")) {
                fsi.save(form);
            }
        }


        return "redirect:/" + HtmlPage.pageForm;
    }
}
