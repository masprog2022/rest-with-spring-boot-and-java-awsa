package com.masprogtechs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masprogtechs.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
