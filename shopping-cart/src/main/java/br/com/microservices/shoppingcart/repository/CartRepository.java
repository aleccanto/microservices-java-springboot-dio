package br.com.microservices.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.microservices.shoppingcart.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {

}
