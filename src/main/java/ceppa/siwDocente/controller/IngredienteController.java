package ceppa.siwDocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ceppa.siwDocente.dto.IngredientiSelectorDTO;
import ceppa.siwDocente.model.Ingrediente;
import ceppa.siwDocente.model.Piatto;
import ceppa.siwDocente.service.CredentialsService;
import ceppa.siwDocente.service.IngredienteService;
import ceppa.siwDocente.service.PiattoService;

@Controller
public class IngredienteController {

	@Autowired IngredienteService ingredienteService;
	@Autowired PiattoService piattoService;
	@Autowired CredentialsService credentialsService;
	
	/*
	 * =========================================
	 * OPERAZIONI ESEGUIBILI CON QUALSIASI RUOLO
	 * =========================================
	 */
	
	@GetMapping("/ingrediente/get/all")
	public String getAllIngredienti(Model model) { 
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
		return "ingredienti.html";
	}
	
	@GetMapping("/ingrediente/get/{id}")
	public String getIngredienteById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.getIngredienteById(id));
		this.credentialsService.setRoleInModel(model);
		return "ingrediente.html";
	}
	
	/* 
	 * ===============================================
	 * OPERAZIONI ESEGUIBILI SOLAMENTE CON RUOLO ADMIN
	 * ===============================================
	 */
	
	@PostMapping("/admin/ingrediente/add")
	public String addIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model) {
		this.ingredienteService.saveIngrediente(ingrediente);
		model.addAttribute("ingrediente", ingrediente); 
		this.credentialsService.setRoleInModel(model);
		return "ingrediente.html";
	}
	
	@GetMapping("/admin/ingrediente/form/add")
	public String getFormIngredienteAdd(Model model) { 
		model.addAttribute("ingrediente", new Ingrediente()); 
		return "ingredienteForm.html";
	}
	
	@GetMapping("/admin/ingrediente/form/modify/{id}") 
	public String getFormIngredienteModify(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteService.getIngredienteById(id));
		return "ingredienteForm.html";
	}
	
	@GetMapping("/admin/ingrediente/form/addToPiatto/p={piattoId}")
	public String getFormSelectIngredienti(@PathVariable("piattoId") Long piattoId, Model model) {    
		Piatto piatto = this.piattoService.getPiattoById(piattoId);
		IngredientiSelectorDTO ingredientiSelectorDTO = new IngredientiSelectorDTO(piattoId, this.ingredienteService.getAllIngredienti(), piatto.getIngredienti());
		model.addAttribute("ingredientiSelectorDTO", ingredientiSelectorDTO);
		return "ingredientiSelector.html"; 
	}
	
	@PostMapping("/admin/piatto/{piattoId}/add/ingredienti")
	public String addIngredientiToPiatto(@PathVariable("piattoId") Long piattoId, @ModelAttribute("ingredientiSelectorDTO") IngredientiSelectorDTO dto, Model model) {
		model.addAttribute("piatto", this.ingredienteService.addIngredientiToPiatto(dto));
		this.credentialsService.setRoleInModel(model);
		return "piatto.html";
	}
	
	@GetMapping("/admin/ingrediente/delete/{id}")
	public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
		this.ingredienteService.deleteIngrediente(id);
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredienti());
		return "ingredienti.html";
	}
	
}
