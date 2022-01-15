package br.com.microservices.shoppingcart.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservices.shoppingcart.model.Cart;
import br.com.microservices.shoppingcart.model.Item;
import br.com.microservices.shoppingcart.repository.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartRepository repository;

	@GetMapping("/{id}")
	public Optional<Cart> findById(@PathVariable Integer id) {
		return repository.findById(id);
	}

	@PostMapping("/{id}")
	public Cart addItens(@PathVariable("id") Integer id, @RequestBody Item item) {
		Optional<Cart> savedCart = repository.findById(id);
		Cart cart = savedCart.isPresent() ? savedCart.get() : new Cart();
		cart.getItems().add(item);
		return repository.save(cart);
	}

	@DeleteMapping("/{id}")
	public void clear(@PathVariable("id") Integer id) {
		repository.deleteById(id);
	}

}
