package au.gov.nla.marc.controller;

import au.gov.nla.marc.service.MarcTextServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportController {

    MarcTextServiceImpl marcTextService;

    public ExportController(MarcTextServiceImpl marcTextService) {
        this.marcTextService = marcTextService;
    }

    @GetMapping(value="/download")
    public String downLoad(Model model){
        model.addAttribute("output", marcTextService.transFormToTabbedOutPut(""));
        return "";

    }
}
