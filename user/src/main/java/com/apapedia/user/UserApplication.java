package com.apapedia.user;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.apapedia.user.model.Admin;
import com.apapedia.user.model.Customer;
import com.apapedia.user.model.RoleEnum;
import com.apapedia.user.model.Seller;
import com.apapedia.user.repository.UserDb;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner generateDummyUser(UserDb userDb, PasswordEncoder passwordEncoder) {
		return args -> {
			Admin newUser0 = new Admin();
			newUser0.setName("Moreno");
			newUser0.setUsername("Moreno00");
			newUser0.setEmail("moreno.rassya@gmail.com");
			newUser0.setPassword(passwordEncoder.encode("secret99"));
			newUser0.setAddress("Mahata Margonda");
			newUser0.setRole(RoleEnum.Admin);
			
			Seller newUser1 = new Seller();
			newUser1.setName("Jimmi");
			newUser1.setUsername("Jimmi01");
			newUser1.setEmail("Jimmi@gmail.com");
			newUser1.setPassword(passwordEncoder.encode("secret100"));
			newUser1.setAddress("Gedung Lama");
			newUser1.setRole(RoleEnum.Seller);
			newUser1.setCategory("Makanan");

			Customer newUser2 = new Customer();
			newUser2.setName("Angel");
			newUser2.setUsername("Angel02");
			newUser2.setEmail("Angel@gmail.com");
			newUser2.setPassword(passwordEncoder.encode("secret101"));
			newUser2.setAddress("Gedung Baru");
			newUser2.setRole(RoleEnum.Customer);
			newUser2.setCartId(UUID.randomUUID());

			userDb.save(newUser0);
			userDb.save(newUser1);
			userDb.save(newUser2);
		};
	}
}
