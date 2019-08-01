package org.java.web;

import com.google.common.primitives.Bytes;
import org.java.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping("loadDocumentTypeRelation")
    public String loadDocumentTypeRelation(@RequestParam Map<String,Object> map,Model model){
        model.addAttribute("data",documentService.findByConditions(map));
        return "documentTypeRelation";
    }

    @RequestMapping("add")
    public String add(@RequestParam Map<String,Object> map){
        documentService.add(map);
        return "redirect:loadDocumentTypeRelation";
    }

    @RequestMapping("update")
    public String update(@RequestParam Map<String,Object> map){
        documentService.update(map);
        return "redirect:loadDocumentTypeRelation";
    }

    @RequestMapping("delete")
    public String delete(String documents_id){
        System.out.println(documents_id);
        documentService.delete(documents_id);
        return "redirect:loadDocumentTypeRelation";
    }


    @RequestMapping("loadUpDocument")
    public String loadUpDocument(String schedule_type_id,String documents_id,Model model){
        String schedule_type_name = "is_vehicle_damage";
        if (schedule_type_id.equals("2")){
            schedule_type_name = "is_human_injury";
        }else if (schedule_type_id.equals("3")){
            schedule_type_name = "is_material_damage";
        }else if (schedule_type_id.equals("4")){
            schedule_type_name = "is_whether_to_steal_or_not";
        }
        model.addAttribute("schedule_type_id",schedule_type_name);
        model.addAttribute("documents_id",documents_id);
        return "upDocument";
    }

    @RequestMapping("showReportCase")
    @ResponseBody
    public Map<String, Object> showReportCase(String schedule_type_id){
        return documentService.showReportCase(schedule_type_id);
    }


    @RequestMapping("upDocument")
    @ResponseBody
    public Map<String,Object> upDocument(@RequestParam("file")MultipartFile file, HttpServletRequest request,@RequestParam Map<String,Object> map) throws IOException {
        System.out.println(map);
        byte[] file_object = file.getBytes();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("msg","ok");
        dataMap.put("code",200);
        map.put("file_object",file_object);
        documentService.addFile(map);
        return dataMap;
    }

    @RequestMapping("showDocumentFile")
    @ResponseBody
    public Map<String,Object> showDocumentFile(String compensate_case_id){
        return documentService.showDocumentFile(compensate_case_id);
    }

    @RequestMapping("showDocumentFileImage")
    public void showDocumentFileImage(String file_id,HttpServletResponse response) throws Exception{
        documentService.showDocumentImage(file_id,response.getOutputStream());
    }

}
