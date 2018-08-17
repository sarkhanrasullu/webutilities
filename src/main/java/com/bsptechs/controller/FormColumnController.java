package com.bsptechs.controller;

import com.bsptechs.beans.FormColumnDto;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.service.inter.FormColumnServiceInter;
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


@Controller
@RequestMapping("formColumns")
public class FormColumnController {

    @Autowired
    FormColumnServiceInter fcsi;


    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model, @ModelAttribute("formColumns") FormColumnDto column,
                            @RequestParam Form formId) {
        List<FormColumn> list = fcsi.findColumnsByFormId(formId);
        model.put("columnList", list);
        model.put("column", column);

        return HtmlPage.pageFormColumn;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("formColumns") FormColumnDto formColumnDto,
            @RequestParam String action,
            @RequestParam Integer formId) {
        Form form = new Form(formId);

        FormColumn formColumn = new FormColumn();
        if(formColumnDto.getId() != null){
            formColumn.setId(formColumnDto.getId());
        }
        formColumn.setName(formColumnDto.getName());
        formColumn.setFormId(form);
        formColumn.setFormWebsite(form);

        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                fcsi.save(formColumn);
            } else if (action.equalsIgnoreCase("delete")) {
                fcsi.deleteById(formColumnDto.getId());
            }
        }


        return "redirect:/formColumns?formId="+formId ;
    }
}
