package org.java.web;

import org.java.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println(map);
        documentService.update(map);
        return "redirect:loadDocumentTypeRelation";
    }

    @RequestMapping("delete")
    public String delete(String documents_id){
        documentService.delete(documents_id);
        return "redirect:loadDocumentTypeRelation";
    }

}
