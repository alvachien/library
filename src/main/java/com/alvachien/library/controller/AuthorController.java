package com.alvachien.library.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvachien.library.exception.AuthorNotFoundException;
import com.alvachien.library.model.Author;
import com.alvachien.library.repository.AuthorRepository;

@RestController
public class AuthorController {
    private final AuthorRepository repository;

    AuthorController(AuthorRepository repository) {
      this.repository = repository;
    }
  
  
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/authors")
    Iterable<Author> all() {
      return repository.findAll();
    }
    // end::get-aggregate-root[]
  
    @PostMapping("/authors")
    Author newAuthor(@RequestBody Author newAuthor) {
      return repository.save(newAuthor);
    }
  
    // Single item
    
    @GetMapping("/authors/{id}")
    Author one(@PathVariable Long id) {
      
      return repository.findById(id)
        .orElseThrow(() -> new AuthorNotFoundException(id));
    }
  
    @PutMapping("/authors/{id}")
    Author replaceAuthor(@RequestBody Author newAuthor, @PathVariable Long id) {
      
      return repository.findById(id)
        .map(author -> {
          author.setNativeName(newAuthor.getNativeName());
          author.setChineseName(newAuthor.getChineseName());
          author.setDetail(newAuthor.getDetail());
          return repository.save(author);
        })
        .orElseGet(() -> {
            newAuthor.setId(id);
            return repository.save(newAuthor);
        });
    }
  
    @DeleteMapping("/authors/{id}")
    void deleteEmployee(@PathVariable Long id) {
      repository.deleteById(id);
    }    
}
