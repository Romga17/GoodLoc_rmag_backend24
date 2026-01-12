package edu.gdlc_project.gdlc_pckgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Goodloc24Application {

	public static void main(String[] args) {
		SpringApplication.run(Goodloc24Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder (){
		return new BCryptPasswordEncoder();
	}
}
