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
import java.util.List;
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
    public String formIndex(Map<String, Object> model, @ModelAttribute("forms") FormDto form) {
        List<Form> list = fsi.findAll();
        model.put("formList", list);
        for(Form f: list){
            System.out.println(f);
        }
        model.put("form",form);
        return HtmlPage.pageForm;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String formCrud(
            @ModelAttribute("forms") FormDto formDto,
            @RequestParam String action) {
        System.out.println("form="+formDto);
        System.out.println("action="+action);


        Form form = new Form();

        form.setId(formDto.getId());

        form.setName(formDto.getName());
        form.setWebsite(formDto.getWebsite());
        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                System.out.println("add process");
                fsi.save(form);
            } else if (action.equalsIgnoreCase("delete")) {
                fsi.deleteById(formDto.getId());
            }
        }


        return "redirect:/forms";
    }
}
