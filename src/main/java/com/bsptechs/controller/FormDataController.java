package com.bsptechs.controller;

import com.bsptechs.beans.FormDataDto;
import com.bsptechs.entities.Form;
import com.bsptechs.entities.FormColumn;
import com.bsptechs.entities.FormData;
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

@Controller
@RequestMapping("formData")
public class FormDataController {

    @Autowired
    FormDataServiceInter fdi;
    @Autowired
    FormColumnServiceInter fci;

    @RequestMapping(method = RequestMethod.GET)
    public String formIndex(Map<String, Object> model,
                            @ModelAttribute("data") FormDataDto data ,
                            @RequestParam Form formId) {
        List<FormColumn> columnList = fci.findColumnsByFormId(formId);
        HashMap<String,List<FormData>> rows = new HashMap<>();
        List<FormData> allDataOfForm = fdi.findAllByFormId(formId);

        for (FormData fc: allDataOfForm) {
            putData(fc.getUid(), fc, rows);
        }
        model.put("rows", rows);
        model.put("columnList", columnList);
        model.put("data", data);

        return HtmlPage.pageFormData;
    }

    public static void putData(String key, FormData obj,  HashMap<String, List<FormData>> dataList){
        List<FormData> data= dataList.get(key);
        if(data!=null){
            data.add(obj);
        }else{
            data = new ArrayList<>();
            data.add(obj);
            dataList.put(key, data);
        }
    }

    @RequestMapping(path = "crud", method = RequestMethod.POST)
    public String userCrud(
            @ModelAttribute("dataForm") FormDataDto formDataDto,
            @RequestParam String action,
            @RequestParam List<Integer> columnId,
            @RequestParam int formId,
            @RequestParam List<Integer> formDataIds) {

            if(action!=null){
                if(action.equalsIgnoreCase("delete")){
                    fdi.deleteFormDataByUId(formDataDto.getUId());
                }else{
                    addOrUpdate(columnId,formDataDto,formId,formDataIds,action);
                }
            }

        return "redirect:/formData?formId="+formId;
    }

    public void addOrUpdate(List<Integer> columnId, FormDataDto formDataDto, int formId,List<Integer> dataId,String action){

        String[] data = formDataDto.getValue().split(",");
        String uId = UUID.randomUUID().toString();

        for (int i= 0; i<data.length;i++) {
            int id =columnId.get(i);
            String value = data[i];

            FormData formData = new FormData();
            formData.setId(formDataDto.getId());
            formData.setValue(value);
            formData.setFormColumnId(new FormColumn(id));
            formData.setFormId(new Form(formId));
            formData.setUid(uId);//delete ve update zamani bu id-ye gore update ve delete edecen
//                formData.setUserId(new User(1));

            if (action != null) {
                if (action.equalsIgnoreCase("add")) {
                    fdi.save(formData);
                } else if (action.equalsIgnoreCase("edit")) {
                    formData.setId(dataId.get(i));
                    formData.setUid(formDataDto.getUId());
                    System.out.println(formData.getUid());
                    fdi.save(formData);
                }
            }
        }
    }



}
