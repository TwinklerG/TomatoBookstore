package com.tomatobookstore.tomatobookstore.repository;

import com.tomatobookstore.tomatobookstore.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductId(Integer productId);

}
