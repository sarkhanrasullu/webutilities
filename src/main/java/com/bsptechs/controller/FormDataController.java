package com.bsptechs.controller;

import com.bsptechs.beans.FormDataDto;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;
import com.bsptechs.entities.User;
import com.bsptechs.service.inter.FormDataServiceInter;
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
@RequestMapping("formData")
public class FormDataController {

    @Autowired
    FormDataServiceInter fdi;

    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model, @ModelAttribute("dataForm") FormDataDto dataForm) {
        ArrayList<FormData> list = fdi.findAll();
        model.put("data", list);
        return HtmlPage.pageFormData;
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("dataForm") FormDataDto formDataDto,
            @RequestParam String action) {
        FormData formData = new FormData();
        formData.setId(formDataDto.getId());
        formData.setFormColumnId(new FormColumn(formDataDto.getFormColumnId()));
        formData.setFormId(new Form(formDataDto.getFormId()));
        formData.setUserId(new User(formDataDto.getUserId()));

        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                fdi.save(formData);
            } else if (action.equalsIgnoreCase("delete")) {
                fdi.deleteById(formDataDto.getId());
            } else if (action.equalsIgnoreCase("update")) {
                formData.setId(formDataDto.getId());
                fdi.save(formData);
            }

        }


        return "redirect:/" + HtmlPage.pageFormData;
    }

}
