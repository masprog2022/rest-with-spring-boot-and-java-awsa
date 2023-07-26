package com.masprogtechs.services;


import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masprogtechs.exceptions.ResourceNotFoundException;
import com.masprogtechs.model.Person;
import com.masprogtechs.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		
	  logger.info("Finding all people!");
	  
	    return repository.findAll();
		
	}
	
	
	public Person findById(Long id) {
		logger.info("Finding one person!");
		
        return repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId())
	        		.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		//person.setId(counter.incrementAndGet());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		repository.delete(entity);
	
	}
	



	
}
