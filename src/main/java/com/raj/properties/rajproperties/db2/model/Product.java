package com.raj.properties.rajproperties.db2.model;
import javax.persistence.*;

@Entity
@Table(schema = "products")
public class Product {

    @Id
    private int id;

    private String name;

    private double price;
}
