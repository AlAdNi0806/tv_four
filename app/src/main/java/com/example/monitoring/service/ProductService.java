package com.example.monitoring.service;

import com.example.monitoring.entity.Product;
import com.example.monitoring.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        return productRepository.findById(id);
    }
    
    public Product createProduct(Product product) {
        logger.info("Creating new product: {}", product.getName());
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        logger.info("Updating product with id: {}", id);
        return productRepository.findById(id)
            .map(product -> {
                product.setName(productDetails.getName());
                product.setDescription(productDetails.getDescription());
                product.setPrice(productDetails.getPrice());
                return productRepository.save(product);
            }).orElse(null);
    }
    
    public void deleteProduct(Long id) {
        logger.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}