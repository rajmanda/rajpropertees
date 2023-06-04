package com.raj.properties.rajproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication

public class RajpropertiesApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate h2JdbcTemplate ;

	@Autowired
	DataSource h2DataSource;

	public static void main(String[] args) {
		SpringApplication.run(RajpropertiesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running") ;
		String dropTableSQL = "DROP TABLE counts ";
		h2JdbcTemplate.execute(dropTableSQL);

		String createTableSQL = "CREATE TABLE counts ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR(255) NOT NULL"
				+ ")";
		h2JdbcTemplate.execute(createTableSQL);
	}
}
