package com.edgar.escuela.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edgar.escuela.models.Administrador;
import com.edgar.escuela.services.AdminService;

@RestController()
@RequestMapping("/v1/api")
public class AdminController {

	// ==========================================================
	// Variables de Instancia
	// ==========================================================

	@Autowired
	private AdminService adminService;

	// ==========================================================
	// Metodos HTTP
	// ==========================================================

	// ==========================================================
	// Retrieve all the Administrators
	// ==========================================================

	@GetMapping("/administrador")
	public ResponseEntity<List<Administrador>> getAll() {
		List<Administrador> listOfAdministrator = adminService.getAll();
		ResponseEntity<List<Administrador>> response = new ResponseEntity<List<Administrador>>( listOfAdministrator, null, HttpStatus.OK);

		return response;
	}

	// ==========================================================
	// Retrieve a specific Administrator
	// ==========================================================

	@GetMapping("/administrador/{id}")
	public ResponseEntity<Resource<Administrador>> getAdministrador(@PathVariable long id) {
		Administrador administrator = adminService.getAdmin(id);
		
		Resource<Administrador> resource = new Resource<Administrador>(administrator);
		
		ControllerLinkBuilder link = linkTo( methodOn(this.getClass()).putAdministrador(administrator) );
		resource.add( link.withRel("Update-Administrator") );
		
		ResponseEntity<Resource<Administrador>> response = new ResponseEntity<Resource<Administrador>>(resource, null, HttpStatus.OK);
		return response;
	}

	// ==========================================================
	// Retrieve a specific Administrator by id and level
	// ==========================================================

	@GetMapping("/administrador/{id}/{level}")
	public ResponseEntity<Administrador> getAdministradorByClaveAndLevel(@PathVariable long id, @PathVariable int level) {
		Administrador administrator = adminService.getAdministradorByIdAndLevel(id, level);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administrator.getId()).toUri();
		responseHeaders.add("Location", location.toString());

		ResponseEntity<Administrador> response = new ResponseEntity<Administrador>(administrator, responseHeaders, HttpStatus.OK);

		return response;
	}

	// ==========================================================
	// Create a new Administrator
	// ==========================================================

	@PostMapping(path = "/administrador")
	public ResponseEntity<Administrador> postAdministrador(@Valid @RequestBody Administrador administrador) {
		Administrador newAdministrator = adminService.addAdmin(administrador);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdministrator.getId()).toUri();
		responseHeaders.add("Location", location.toString());

		ResponseEntity<Administrador> response = new ResponseEntity<Administrador>(newAdministrator, responseHeaders, HttpStatus.CREATED);

		return response;
	}

	// ==========================================================
	// Modify an already created Administrator
	// ==========================================================

	@PutMapping(path = "/administrador")
	public ResponseEntity<Administrador> putAdministrador(@Valid @RequestBody Administrador administrador) {
		Administrador newAdministrator = adminService.updateAdmin(administrador);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdministrator.getId()).toUri();
		responseHeaders.add("Location", location.toString());

		ResponseEntity<Administrador> response = new ResponseEntity<Administrador>( newAdministrator, responseHeaders, HttpStatus.OK);
		return response;
	}
	
	// ==========================================================
	// Delete an Administrator
	// ==========================================================
	
	@DeleteMapping(path = "/administrador/{id}")
	public ResponseEntity<String> deleteAdministrador(@Valid @PathVariable long id) {
		adminService.deleteAdminLevelOne(id);
		ResponseEntity<String> response = new ResponseEntity<String>( "Administrador eliminado correctamente", null, HttpStatus.OK);
		return response;
	}

}
