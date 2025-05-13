package ru.kors.springstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.kors.springstudents.model.Student;

import static org.springframework.security.config.Customizer.withDefaults;


@SpringBootApplication
public class SpringStudentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudentsApplication.class, args);
	}



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic(withDefaults())  // импорт static org.springframework.security.config.Customizer.withDefaults;
				.authorizeHttpRequests(auth -> auth
						.anyRequest().authenticated()
				);
		return http.build();
	}

}
