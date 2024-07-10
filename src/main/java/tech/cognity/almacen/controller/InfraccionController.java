package tech.cognity.almacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tech.cognity.almacen.entity.Infraccion;
import tech.cognity.almacen.service.InfraccionService;

@RestController
@RequestMapping("/v1/infracciones")
public class InfraccionController {
	@Autowired
	private InfraccionService service;
	
	@GetMapping()
	public ResponseEntity<List<Infraccion>> findAll(
			@RequestParam(value = "dni", required = false, defaultValue = "") String dni,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumber,pageSize);
		List<Infraccion> infracciones;
		if(dni==null) {
			infracciones=service.findAll(page);
		}else {
			infracciones=service.findByDni(dni, page);
		}
		
		if(infracciones.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(infracciones);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Infraccion> findById(@PathVariable("id") int id){
		Infraccion infraccion = service.findById(id);
		if(infraccion==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(infraccion);			
	}
	
	@PostMapping()
	public ResponseEntity<Infraccion> create(@RequestBody Infraccion infraccion){
		Infraccion registro=service.save(infraccion);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Infraccion> update(@PathVariable("id") int id, @RequestBody Infraccion infraccion){
		Infraccion registro=service.update(infraccion);
		if(registro==null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(registro);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Infraccion> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
}
