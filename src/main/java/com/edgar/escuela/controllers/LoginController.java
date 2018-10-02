package com.edgar.escuela.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.escuela.models.Administrador;

@RestController
@RequestMapping("/v2")
public class LoginController {
	
	@PostMapping(path="/method")
    public String index( @RequestBody Administrador administrador, HttpServletRequest request) {
		
		if( administrador.getId() == 1 && administrador.getPassword().equals("test") ) {
			
			request.getSession().setAttribute("session", true);
			return "exito";
			
		} else {
			
			return "falso";
			
		}
    }
	
}
