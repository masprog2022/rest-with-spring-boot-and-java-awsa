package com.masprogtechs.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masprogtechs.BookController;
import com.masprogtechs.data.vo.v1.BookVO;
import com.masprogtechs.exceptions.RequireObjectlsNullException;
import com.masprogtechs.exceptions.ResourceNotFoundException;
import com.masprogtechs.mapper.DozerMapper;
import com.masprogtechs.model.Book;
import com.masprogtechs.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;

	public List<BookVO> findAll() {

		logger.info("Finding all book!");

		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Long id) {

		logger.info("Finding one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	public BookVO create(BookVO book) {
		if (book == null)
			throw new RequireObjectlsNullException();

		logger.info("Creating one book!");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	public BookVO update(BookVO book) {
if (book == null) throw new RequireObjectlsNullException();
		
		logger.info("Updating one book!");
		
		var entity = repository.findById(book.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one book!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum registo encontrado para este ID!"));
		repository.delete(entity);
	}

}
