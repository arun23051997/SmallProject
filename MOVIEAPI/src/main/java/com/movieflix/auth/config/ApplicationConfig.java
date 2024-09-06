package com.movieflix.auth.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.movieflix.auth.repositories.UserRepository;
//
//@Configuration
//public class ApplicationConfig {
//
//	private final UserRepository userRepository;
//
//    public ApplicationConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//@Configuration

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	 

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	        return httpSecurity
	                .csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(registry -> {
	                    registry.requestMatchers("/register/user", "/register/**").permitAll();
	                    registry.requestMatchers("/api/v1/movie/add-movie").hasRole("ADMIN");
	                    registry.requestMatchers("/api/v1/movie/all").hasRole("ADMIN");
	                    registry.requestMatchers("/api/v1/movie/allMoviesPageSort").hasRole("ADMIN");
	                    registry.requestMatchers("/api/v1/movie/delete/{movieId}").hasRole("ADMIN");
	                    registry.requestMatchers("/api/v1/movie/allMoviesPage").hasRole("ADMIN");

	                    registry.requestMatchers("/api/v1/movie/{movieId}").hasRole("USER");
	                    registry.anyRequest().authenticated();
	                    })
	                    .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//	                })
//	                .formLogin(httpSecurityFormLoginConfigurer -> {
//	                    httpSecurityFormLoginConfigurer
//	                            .loginPage("/login")
//	                            .successHandler(new AuthenticationSuccessHandler())
//	                            .permitAll();
	               
	                .build();
	    }
	    
	    @Bean
	    public UserDetailsService userDetailsService() {
	        return userDetailsService;
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}