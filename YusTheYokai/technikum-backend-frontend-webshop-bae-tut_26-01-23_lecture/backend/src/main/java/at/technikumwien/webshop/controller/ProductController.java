package at.technikumwien.webshop.controller;

import java.util.List;

import at.technikumwien.webshop.model.Product;

import at.technikumwien.webshop.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductRepository repo;

    @GetMapping
    public List<Product> findAllProducts() {
        return repo.findAll();
    }

    @GetMapping("/{type}")
    public List<Product> findAllProductsByType(@PathVariable String type) {
        return repo.findByType(type);
    }
}
