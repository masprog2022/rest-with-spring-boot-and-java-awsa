package com.masprogtechs.services;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masprogtechs.data.vo.v1.PersonVO;
import com.masprogtechs.exceptions.ResourceNotFoundException;
import com.masprogtechs.mapper.DozerMapper;
import com.masprogtechs.model.Person;
import com.masprogtechs.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<PersonVO> findAll() {
		
	  logger.info("Finding all people!");
	  
	    return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		
	}
	
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
        var entity = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
        
        return DozerMapper.parseObject(entity, PersonVO.class);
		
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class); 
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
		
		
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId())
	        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		//person.setId(counter.incrementAndGet());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
        var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		repository.delete(entity);
	
	}
	



	
}
