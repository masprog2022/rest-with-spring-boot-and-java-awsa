package com.masprogtechs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.masprogtechs.model.Person;

@Service
public class PersonService {

	private static final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		logger.info("Finding one person!");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Mauro");
		person.setLastName("Manuel");
		person.setAddress("Luanda - Centralidade do Kilamba Bloco G32 apt 92");
		person.setGender("Male");
        return person;
		
	}
	
	public List<Person> findAll() {
		
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i<8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
		
	}

	private Person mockPerson(int i) {
		
	Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("First name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Angola " + i);
		person.setGender("Male");
		return person;
	}
	
}
