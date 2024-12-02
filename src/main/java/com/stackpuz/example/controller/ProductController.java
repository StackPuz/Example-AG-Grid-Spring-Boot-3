package com.stackpuz.example.controller;

import java.util.Optional;

import com.stackpuz.example.entity.Product;
import com.stackpuz.example.repository.ProductRepository;
import com.stackpuz.example.dto.AgGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/api/products")
    public AgGrid<Product> getProducts(@RequestParam("page") Optional<Integer> pageParam, @RequestParam("size") Optional<Integer> sizeParam, @RequestParam("order") Optional<String> orderParam, @RequestParam("direction") Optional<String> directionParam, @RequestParam("search") Optional<String> searchParam) {
        int page = pageParam.orElse(1) - 1;
        int size = sizeParam.orElse(10);
        String order = orderParam.orElse("id");
        String direction = directionParam.orElse("asc");
        String search = searchParam.orElse("");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), order));
        Page<Product> pageProduct = (search.isEmpty() ? repository.findAll(pageRequest) : repository.findByNameContains(pageRequest, search));
        return new AgGrid<Product>(pageProduct.getTotalElements(), pageProduct.getContent());
    }
}