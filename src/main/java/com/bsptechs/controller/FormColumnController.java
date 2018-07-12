package com.bsptechs.controller;

import com.bsptechs.beans.FormColumnDto;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.service.inter.FormColumnServiceInter;
import com.bsptechs.util.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;


@Controller
@RequestMapping("formColumns")
public class FormColumnController {

    @Autowired
    FormColumnServiceInter fcsi;

    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model, @ModelAttribute("formColumns") FormColumnDto formColumn) {
        ArrayList list = fcsi.findAll();
        model.put("formColumns", list);
        return HtmlPage.pageFormColumn;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("formColumns") FormColumnDto formColumnDto,
            @RequestParam String action) {
        FormColumn formColumn = new FormColumn(formColumnDto.getId());
        formColumn.setName(formColumnDto.getName());
        formColumn.setFormId(new Form(formColumnDto.getId()));
        formColumn.setFormWebsite(new Form(formColumnDto.getFormWebsite()));

        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                fcsi.save(formColumn);
            } else if (action.equalsIgnoreCase("delete")) {
                fcsi.deleteById(formColumnDto.getId());
            } else if (action.equalsIgnoreCase("update")) {
                fcsi.save(formColumn);
            }
        }


        return "redirect:/" + HtmlPage.pageFormColumn;
    }
}
