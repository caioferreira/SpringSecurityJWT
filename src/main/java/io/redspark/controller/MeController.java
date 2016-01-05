package io.redspark.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

	@Secured("ROLE_USUARIO")
	@RequestMapping("/security")
	public String security(Authentication authentication){
		return "Acessando serviço seguro";
	}
	
	@RequestMapping("/open")
	public String open(Authentication authentication){
		return "Acessando serviço não seguro";
	}
}
