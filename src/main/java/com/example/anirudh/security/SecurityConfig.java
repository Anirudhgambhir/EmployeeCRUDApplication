package com.example.anirudh.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails employeeUser = User.builder().username("anirudh").password("{noop}Anirudh10").roles("EMPLOYEE").build();

        UserDetails managerUser = User.builder().username("shivam").password("{noop}Shivam10").roles("EMPLOYEE", "MANAGER").build();

        UserDetails adminUser = User.builder().username("marshal").password("{noop}Marshal10").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(employeeUser, managerUser, adminUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer -> {
            configurer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "api/companies/**").hasRole("EMPLOYEE");
        });

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
