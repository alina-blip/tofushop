package at.technikumwien.webshop.repository;

import java.util.List;

import at.technikumwien.webshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findAllByType(String type);
}

