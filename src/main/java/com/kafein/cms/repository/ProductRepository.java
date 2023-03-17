package com.kafein.cms.repository;

import com.kafein.cms.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByName(String name);
    Optional<List<Product>> findByWarehouseId(Long warehouseId);
    Optional<List<Product>> findAllByDeletedEquals(Boolean deleted);
}
