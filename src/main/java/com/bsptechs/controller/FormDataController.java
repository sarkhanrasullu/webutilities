package com.bsptechs.controller;

import com.bsptechs.beans.FormDataDto;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;
import com.bsptechs.entities.User;
import com.bsptechs.service.inter.FormColumnServiceInter;
import com.bsptechs.service.inter.FormDataServiceInter;
import com.bsptechs.util.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


@Controller
@RequestMapping("formData")
public class FormDataController {

    @Autowired
    FormDataServiceInter fdi;
    @Autowired
    FormColumnServiceInter fci;


    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model, @ModelAttribute("data") FormDataDto data ,
                            @RequestParam Form formId) {


        List<FormColumn> columnList = fci.findColumnsByFormId(formId);
        for (FormColumn columns : columnList) {
            List<FormData> list = fdi.findAllByFormColumnId(columns);
            model.put("dataList", list);
        }
        model.put("columnList", columnList);
            model.put("data", data);

            return HtmlPage.pageFormData;
        }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("dataForm") FormDataDto formDataDto,
            @RequestParam String action,@RequestParam int columnId,
            @RequestParam int formId) {

        String[] data = formDataDto.getValue().split(",");
        for (String fd : data){
        FormData formData = new FormData();
        formData.setId(formDataDto.getId());
        formData.setValue(fd);
        formData.setFormColumnId(new FormColumn(columnId));
        formData.setFormId(new Form(formId));
        formData.setUserId(new User(1));

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
        }


        return "redirect:/formData";
    }

}
