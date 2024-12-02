package com.stackpuz.example.repository;

import com.stackpuz.example.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContains(Pageable pageable, String name);

}