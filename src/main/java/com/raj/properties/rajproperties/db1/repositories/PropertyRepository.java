package com.raj.properties.rajproperties.db1.repositories;

import com.raj.properties.rajproperties.db1.models.Property;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}

