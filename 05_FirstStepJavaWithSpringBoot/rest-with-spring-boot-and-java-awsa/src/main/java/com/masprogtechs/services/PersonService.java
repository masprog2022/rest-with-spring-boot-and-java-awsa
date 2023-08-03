package com.masprogtechs.services;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masprogtechs.PersonController;
import com.masprogtechs.data.vo.v1.PersonVO;

import com.masprogtechs.exceptions.RequireObjectlsNullException;
import com.masprogtechs.exceptions.ResourceNotFoundException;
import com.masprogtechs.mapper.DozerMapper;
import com.masprogtechs.mapper.custom.PersonMapper;
import com.masprogtechs.model.Person;
import com.masprogtechs.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<PersonVO> findAll() {
		
	  logger.info("Finding all people!");
	  
	    var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	    
	    persons
	           .stream()
	           .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
	    
	    return persons;
		
	}
	
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
        var entity = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
        
         var vo = DozerMapper.parseObject(entity, PersonVO.class);
         
         vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        
        return vo;
		
	}
	
	public PersonVO create(PersonVO person) {
		
		if (person == null) throw new RequireObjectlsNullException();
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class); 
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
	        
	    return vo;
	}

	
	public PersonVO update(PersonVO person) {
		
		if (person == null) throw new RequireObjectlsNullException();
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey())
	        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		//person.setId(counter.incrementAndGet());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
	        
	   return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		repository.delete(entity);
	
	}
	



	
}
