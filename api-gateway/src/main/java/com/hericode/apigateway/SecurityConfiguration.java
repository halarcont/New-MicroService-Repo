package com.hericode.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

//especifica que todas las solicitudes deben estar autenticadas utilizando el método authorizeExchange
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
//        httpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
//                .oauth2Login(Customizer.withDefaults());
//        httpSecurity.csrf().disable();
//
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                //ALLOWING REGISTER API FOR DIRECT ACCESS
                //.pathMatchers("/user/api/v1/register").permitAll()
                //ALL OTHER APIS ARE AUTHENTICATED
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .oauth2Login()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }


}
