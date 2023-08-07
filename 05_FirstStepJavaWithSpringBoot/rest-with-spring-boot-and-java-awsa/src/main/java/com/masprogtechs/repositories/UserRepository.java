package com.masprogtechs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masprogtechs.model.Person;
import com.masprogtechs.model.User;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
	
	@Query("SELECT u FROM User WHERE u.username =:userName")
	User findByUsername(@Param("userName") String userName);
}
