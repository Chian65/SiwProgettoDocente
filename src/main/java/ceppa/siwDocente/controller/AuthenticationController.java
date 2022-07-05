package ceppa.siwDocente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ceppa.siwDocente.model.Credentials;
import ceppa.siwDocente.model.Utente;
import ceppa.siwDocente.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired private CredentialsService credentialsService;
	
	//@Autowired private UtenteValidator utenteValidator;
	
	// @Autowired private CredentialsValidator credentialsValidator;
	
	@GetMapping("/register")
	public String showRegisterForm (Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "registerForm";
	}
	
	@GetMapping(value = {"/login", "/", "/index", "/logout"})
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@GetMapping("/home")
    public String home(Model model) {
		this.credentialsService.setRoleInModel(model);
        return "home";
    }
	
    @PostMapping("/register") 
    public String registerUser(@ModelAttribute("utente") Utente utente,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        //this.userValidator.validate(user, userBindingResult);
        //this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUtente(utente);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("username", credentials.getUsername()); 
            return "loginForm";
        }
        return "registerForm";
    }
}

