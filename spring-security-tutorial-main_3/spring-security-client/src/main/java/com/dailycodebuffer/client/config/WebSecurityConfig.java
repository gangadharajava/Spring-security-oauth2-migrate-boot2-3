package com.dailycodebuffer.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/hello",
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*
		 * http .cors() .and() .csrf() .disable() .authorizeRequests()
		 * .antMatchers(WHITE_LIST_URLS).permitAll()
		 * .antMatchers("/api/**").authenticated() .and() .oauth2Login(oauth2login ->
		 * oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
		 * .oauth2Client(Customizer.withDefaults());
		 */
    	
    	http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers("/api/**").authenticated()
        )
        .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/oauth2/authorization/api-client-oidc")
        ).oauth2Client(Customizer.withDefaults());
        return http.build();
    }
}
