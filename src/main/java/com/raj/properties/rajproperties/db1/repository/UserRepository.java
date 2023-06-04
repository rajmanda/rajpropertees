package com.raj.properties.rajproperties.db1.repository;

import com.raj.properties.rajproperties.db1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Integer> { }
