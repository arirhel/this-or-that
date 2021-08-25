package com.arirhel.thisorthat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // https://www.djamware.com/post/5b2f000880aca77b083240b2/spring-boot-security-and-data-mongodb-authentication-example

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todo
        // http.headers().contentSecurityPolicy("script-src 'self'");
        http
          .authorizeRequests()
          .antMatchers("/").permitAll()
          // .antMatchers("/login").permitAll() todo add login page
          .antMatchers("/choose").permitAll()
          .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/styles/**", "/js/**");
    }

}
