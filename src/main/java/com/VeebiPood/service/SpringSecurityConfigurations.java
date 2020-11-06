package com.VeebiPood.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /*.antMatchers("/", "/home").permitAll()*/  //ilma suunab automaatselt login lehele
                /*.anyRequest().authenticated()*/
                .anyRequest().permitAll()   // NB see teeb paroolivabaks kõik päringud
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable(); //lubab vist ka muud päringud kui "GET"
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


//
//Turvalahendustes on 2 asja:
//1. sessioni ID
//2. mingi web token nagu www.jwt.io