package ceppa.siwDocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ceppa.siwDocente.model.Buffet;
import ceppa.siwDocente.service.BuffetService;
import ceppa.siwDocente.service.ChefService;
import ceppa.siwDocente.service.CredentialsService;

@Controller
public class BuffetController {

	@Autowired BuffetService buffetService; 
	@Autowired ChefService chefService;
	@Autowired CredentialsService credentialsService;
	
	/*
	 * =========================================
	 * OPERAZIONI ESEGUIBILI CON QUALSIASI RUOLO
	 * =========================================
	 */
	
	@GetMapping("/buffet/get/all")
	public String getAllBuffets(Model model) {
		model.addAttribute("buffets", this.buffetService.getAllBuffets());
		return "buffets.html";
	}
	
	@GetMapping("/buffet/get/{id}")
	public String getBuffetById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.getBuffetById(id));
		this.credentialsService.setRoleInModel(model);
		return "buffet.html";
	}
	
	@GetMapping("/buffet/get/chef={chefId}")
	public String getAllBuffetsByChefId(@PathVariable("chefId") Long chefId, Model model) {
		model.addAttribute("buffets", this.buffetService.getAllBuffetsByChefId(chefId));
		return "buffets.html";
	}
	
	/* 
	 * ===============================================
	 * OPERAZIONI ESEGUIBILI SOLAMENTE CON RUOLO ADMIN
	 * ===============================================
	 */
	
	@PostMapping("/admin/buffet/add")
	public String addBuffet(@ModelAttribute("buffet") Buffet buffet, Model model) {
		if(buffet.getId() != null)
			model.addAttribute("modified",true);
		this.buffetService.saveBuffet(buffet);	
		model.addAttribute("buffet", buffet);
		this.credentialsService.setRoleInModel(model);
		return "buffet.html";
	}
	
	@GetMapping("/admin/buffet/form/add")
	public String getFormAddBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chefs", this.chefService.getAllChefs());
		model.addAttribute("modifyForm", false);
		return "buffetForm.html";
	}
	
	@GetMapping("/admin/buffet/form/modify/{id}")
	public String getFormModifyBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.getBuffetById(id));
		model.addAttribute("chefs", this.chefService.getAllChefs());
		model.addAttribute("modifyForm", true);
		return "buffetForm.html";
	}
	
	@PostMapping("/admin/buffet/delete/{id}")
	public String deleteBuffetById(@PathVariable("id") Long id, Model model) {
		this.buffetService.deleteBuffetById(id);
		model.addAttribute("buffets", this.buffetService.getAllBuffets());
		return "buffets.html";
	}
	
}
