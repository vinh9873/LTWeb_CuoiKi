package vn.ute.config;

import vn.ute.service.security.UserWebDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vn.ute.service.security.UserWebDetailsService;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {
	 @Autowired
	    UserWebDetailsService userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        // return new BCryptPasswordEncoder();
	        return NoOpPasswordEncoder.getInstance();
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/").permitAll()
	                        .anyRequest().authenticated())
	                .formLogin(form -> form.loginPage("/login").permitAll())
	                .logout(logout -> logout.logoutSuccessUrl("/"))
	                .authenticationManager(authenticationManager())
	                .build();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager() {
	        var authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService);
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return new ProviderManager(authenticationProvider);
	    }
}
