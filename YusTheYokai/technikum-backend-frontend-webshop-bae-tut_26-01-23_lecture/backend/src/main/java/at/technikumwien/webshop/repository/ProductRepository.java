package at.technikumwien.webshop.repository;

import java.util.List;

import at.technikumwien.webshop.model.Product;

public interface ProductRepository {

    List<Product> findAll();

    List<Product> findAllByType(String type);
}
