package br.com.microservices.productcatalog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.microservices.productcatalog.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
