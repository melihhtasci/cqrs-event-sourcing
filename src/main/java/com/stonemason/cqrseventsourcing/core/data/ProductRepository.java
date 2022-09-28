package com.stonemason.cqrseventsourcing.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByProductId(String productId);
}
