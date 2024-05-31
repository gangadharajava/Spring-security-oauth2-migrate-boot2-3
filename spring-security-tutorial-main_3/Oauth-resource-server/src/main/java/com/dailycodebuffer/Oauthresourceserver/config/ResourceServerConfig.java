package com.dailycodebuffer.Oauthresourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig  {

    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		  /*http .authorizeRequests()
		  .requestMatchers("/api/**").access("hasAuthority('SCOPE_api.read')")
		   .oauth2ResourceServer() .jwt();*/
		  http.authorizeHttpRequests (auth ->
          auth.requestMatchers("/api/**").hasAuthority("hasAuthority('SCOPE_api.read')").anyRequest().authenticated()
      )
      .oauth2ResourceServer ( oauth2 -> oauth2.jwt(Customizer.withDefaults()) );
      
        return http.build();
    }
}
