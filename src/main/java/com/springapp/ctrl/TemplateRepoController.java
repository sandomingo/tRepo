package com.springapp.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class TemplateRepoController {
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model) {
        Integer siteTemplateNum = 0;
        model.addAttribute("siteTemplateNum", siteTemplateNum);
        Integer searchTemplateNum = 0;
        model.addAttribute("searchTemplateNum", searchTemplateNum);
        return "index";
	}
}