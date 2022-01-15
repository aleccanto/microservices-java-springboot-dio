package br.com.microservices.productcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.productcatalog.model.Product;
import br.com.microservices.productcatalog.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductRepository repository;

	@PostMapping
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}

	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(NotFoundException::new);
	}

	@GetMapping
	public Iterable<Product> findAll() {
		return repository.findAll();
	}
}
