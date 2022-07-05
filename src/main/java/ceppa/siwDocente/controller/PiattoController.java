package ceppa.siwDocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ceppa.siwDocente.dto.PiattiSelectorDTO;
import ceppa.siwDocente.model.Buffet;
import ceppa.siwDocente.model.Piatto;
import ceppa.siwDocente.service.BuffetService;
import ceppa.siwDocente.service.ChefService;
import ceppa.siwDocente.service.CredentialsService;
import ceppa.siwDocente.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired PiattoService piattoService; 
	@Autowired ChefService chefService;
	@Autowired BuffetService buffetService;
	@Autowired CredentialsService credentialsService;
	
	/*
	 * =========================================
	 * OPERAZIONI ESEGUIBILI CON QUALSIASI RUOLO
	 * =========================================
	 */
	
	@GetMapping("/piatto/get/all")
	public String getAllPiatti(Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "piatti.html";
	}
	
	@GetMapping("/piatto/get/{id}")
	public String getPiattoById(@PathVariable("id") Long id, Model model) { 
		model.addAttribute("piatto", this.piattoService.getPiattoById(id));
		this.credentialsService.setRoleInModel(model);
		return "piatto.html";
	}
	
	@GetMapping("/piatto/get/chef={chefId}")
	public String getAllPiattiByChefId(@PathVariable("chefId") Long chefId, Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiattiByChefId(chefId));
		return "piatti.html"; 
	}
	
	/* 
	 * ===============================================
	 * OPERAZIONI ESEGUIBILI SOLAMENTE CON RUOLO ADMIN
	 * ===============================================
	 */
	
	@PostMapping("/admin/piatto/add")
	public String addPiatto(@ModelAttribute("piatto") Piatto piatto, Model model) {  
		this.piattoService.savePiatto(piatto);
		model.addAttribute("piatto", piatto);
		this.credentialsService.setRoleInModel(model);
		return "piatto.html";
	}
	
	@GetMapping("/admin/piatto/form/add")
	public String getFormAddPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("chefs", this.chefService.getAllChefs());
		model.addAttribute("modifyForm", false); 
		return "piattoForm.html"; 
	}
	
	@GetMapping("/admin/piatto/form/modify/{id}")  
	public String getFormModifyPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", this.piattoService.getPiattoById(id));
		model.addAttribute("chefs", this.chefService.getAllChefs());			
		model.addAttribute("modifyForm", true); 
		return "piattoForm.html";
	}
	 
	@GetMapping("/admin/piatto/form/addToBuffet/c={chefId}b={buffetId}")   
	public String getFormSelectPiattiByChefId(@PathVariable("chefId") Long chefId, @PathVariable("buffetId") Long buffetId, Model model) {    
		Buffet buffet = this.buffetService.getBuffetById(buffetId);
		PiattiSelectorDTO piattiSelectorDTO = new PiattiSelectorDTO(buffetId, this.piattoService.getAllPiattiByChefId(chefId), buffet.getPiatti());
		model.addAttribute("piattiSelectorDTO", piattiSelectorDTO);
		return "piattiSelector.html"; 
	}
	
	@PostMapping("/admin/buffet/{buffetId}/add/piatti")
	public String addPiattiToBuffet(@PathVariable("buffetId") Long buffetId, @ModelAttribute("piattiSelectorDTO") PiattiSelectorDTO dto, Model model) {
		model.addAttribute("buffet", this.piattoService.addPiattiToBuffet(dto));
		this.credentialsService.setRoleInModel(model);
		return "buffet.html";
	}
	
	@PostMapping("/admin/piatto/delete/{id}") 
	public String deletePiattoById(@PathVariable("id") Long id, Model model) {
		this.piattoService.deletePiattoById(id);
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "piatti.html";
	}
	
}
