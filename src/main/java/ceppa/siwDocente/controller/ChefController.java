package ceppa.siwDocente.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ceppa.siwDocente.model.Chef;
import ceppa.siwDocente.service.ChefService;
import ceppa.siwDocente.service.CredentialsService;

@Controller
public class ChefController {

	@Autowired ChefService chefService;
	@Autowired CredentialsService credentialsService;
	
	/*
	 * =========================================
	 * OPERAZIONI ESEGUIBILI CON QUALSIASI RUOLO
	 * =========================================
	 */
	
	@GetMapping("/chef/get/all")
	public String getAllChefs(Model model) {
		model.addAttribute("chefs", this.chefService.getAllChefs());
		return "chefs.html";
	}
	
	@GetMapping("/chef/get/{id}")
	public String getChefById(@PathVariable("id") long id, Model model) {
		model.addAttribute("chef", this.chefService.getChefById(id));
		this.credentialsService.setRoleInModel(model);
		return "chef.html";
		
	}

	
	
	
	/* 
	 * ===============================================
	 * OPERAZIONI ESEGUIBILI SOLAMENTE CON RUOLO ADMIN
	 * ===============================================
	 */
	
	@PostMapping("/admin/chef/add") 
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) { 
		if (!bindingResult.hasErrors()) { 
			if (chef.getId() != null)
				model.addAttribute("modified", true);
			this.chefService.saveChef(chef); 
			model.addAttribute("chef", chef); //Aggiungo al template il model chef, con i dati inseriti inizialmente nella form
			//model.addAttribute("admin", true);
			this.credentialsService.setRoleInModel(model);
			return "chef.html"; //Torno il template
		}
		return "chefForm.html"; //Torno il template della form poiché sono stati rilevati errori
	}
	
	@GetMapping("/admin/chef/form/add")
	public String getFormAddChef(Model model) {   
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	@GetMapping("/admin/chef/form/modify/{id}")
	public String getFormModifyChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.getChefById(id));
		model.addAttribute("modifyForm", true); 
		return "chefForm.html";
	}
	
	@PostMapping("/admin/chef/delete/{id}")
	public String deleteChefById(@PathVariable("id") Long id, Model model) {
		this.chefService.deleteChefById(id);
		model.addAttribute("chefs", this.chefService.getAllChefs());
		return "chefs.html";
	}
	
}
