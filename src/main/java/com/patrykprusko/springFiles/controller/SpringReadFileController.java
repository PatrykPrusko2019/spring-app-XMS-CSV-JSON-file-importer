package com.patrykprusko.springFiles.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.patrykprusko.springFiles.model.Policy;
import com.patrykprusko.springFiles.service.SpringReadFileService;

@Controller
public class SpringReadFileController {
	
	@Autowired 
	private SpringReadFileService springReadFileService;
	
	@GetMapping(value="/") 
	public String home(Model model) {
		model.addAttribute("policy", new Policy());
		List<Policy> policy = springReadFileService.findAll();
		model.addAttribute("_policy", policy);
		return "view/_policy";
	}
	
	@PostMapping(value="/fileupload")
	public String uploadFile(@ModelAttribute Policy policy, RedirectAttributes redirectAttributes) {
		boolean isFlag = springReadFileService.saveDataFromUploadFile(policy.getFile());
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successMessage", "File Upload Successfully!");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "File Upload not done, please try again!");
		}
		
		return "redirect:/";
	}
}
