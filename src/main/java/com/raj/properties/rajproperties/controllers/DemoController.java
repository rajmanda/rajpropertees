package com.raj.properties.rajproperties.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")

public class DemoController {

    @Autowired
    @Qualifier("db1JdbcTemplate")
    JdbcTemplate db1JdbcTemplate;

    @Autowired
    @Qualifier("db2JdbcTemplate")
    JdbcTemplate db2JdbcTemplate;

    @Autowired
    @Qualifier("h2JdbcTemplate")
    JdbcTemplate h2JdbcTemplate ;



    @GetMapping("/db1")
    public String helloCinemas() {

        String sqldb1 = "SELECT * FROM movies";
        AtomicReference<String> name= new AtomicReference<>("");
        db1JdbcTemplate.query(sqldb1, (rs, rowNum) -> {
            //int id = rs.getInt("id");
            name.set(rs.getString("title"));
            System.out.println( "FROM  DB1.MOVIES - title: " + name);
            return null;
        });

        h2JdbcTemplate.execute("INSERT INTO counts (name) VALUES('"+name+"')");
        return "Title added to DB1.MOVIES  - " + name;
    }

    @GetMapping("/db2")
    public String helloMovies() {

        String sqldb2 = "SELECT * FROM cinemas";
        AtomicReference<String> name = new AtomicReference<>("");
        db2JdbcTemplate.query(sqldb2, (rs, rowNum) -> {
            //int id = rs.getInt("id");
            name.set(rs.getString("title"));
            System.out.println( "FROM  DB2.CINEMAS - title: " + name);
            return null;
        });
        h2JdbcTemplate.execute("INSERT INTO counts (name) VALUES('"+name+"')");
        return "Title added to DB2.CINEMAS  - " + name;
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}