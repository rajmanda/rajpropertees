package com.raj.properties.rajproperties.db2.model.repository;

import com.raj.properties.rajproperties.db2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Integer> { }
