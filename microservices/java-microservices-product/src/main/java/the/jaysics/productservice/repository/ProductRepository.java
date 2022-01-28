package the.jaysics.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import the.jaysics.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
