package tech.cognity.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.cognity.almacen.entity.Infraccion;
import tech.cognity.almacen.repository.InfraccionRepository;
import tech.cognity.almacen.service.InfraccionService;

@Service
public class InfraccionServiceImpl implements InfraccionService{
	@Autowired
	private InfraccionRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Infraccion findById(int id) {
		try {
			Infraccion registro=repository.findById(id).orElseThrow();
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Infraccion save(Infraccion infraccion) {
		try {
			infraccion.setActivo(true);
			Infraccion registro=repository.save(infraccion);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Infraccion update(Infraccion infraccion) {
		try {
			Infraccion registro = repository.findById(infraccion.getId()).orElseThrow();
			registro.setDni(infraccion.getDni());
			registro.setTipo_infraccion(infraccion.getTipo_infraccion());
			registro.setUbicacion(infraccion.getUbicacion());
			registro.setDescripcion(infraccion.getDescripcion());
			repository.save(registro);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			Infraccion registro=repository.findById(id).orElseThrow();
			repository.delete(registro);
		}catch(Exception e) {
			
		}
		
	}	

}
