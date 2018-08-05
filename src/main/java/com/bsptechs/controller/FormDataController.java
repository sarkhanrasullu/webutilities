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

import java.util.*;
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

        HashMap<FormColumn,List<FormData>> dataList = new HashMap<>();
        List<FormColumn> columnList = fci.findColumnsByFormId(formId);
        for (int i=0; i<columnList.size();i++) {
            List<FormData> list = fdi.findAllByFormColumnId(columnList.get(i));
            for (int j = 0; j < list.size(); j++) {
                dataList.put(columnList.get(i), list);
            }
        }
        model.put("dataList", dataList);
        model.put("columnList", columnList);
            model.put("data", data);

            return HtmlPage.pageFormData;
        }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("dataForm") FormDataDto formDataDto,
            @RequestParam String action,@RequestParam List<Integer> columnId,
            @RequestParam int formId) {
        HashMap<Integer,String> map = new HashMap<>();
        String[] data = formDataDto.getValue().split(",");

        for (int i= 0; i<data.length;i++) {

            map.put(columnId.get(i), data[i]);
        }
            Iterator<Map.Entry<Integer,String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<Integer, String> entry = entries.next();

                FormData formData = new FormData();
                formData.setId(formDataDto.getId());
                formData.setValue(entry.getValue());
                formData.setFormColumnId(new FormColumn(entry.getKey()));
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
