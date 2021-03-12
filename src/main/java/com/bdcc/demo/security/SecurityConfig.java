package com.bdcc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder password=passwordEncoder();
        auth.inMemoryAuthentication().withUser("yaz").password(password.encode("1234")).roles("ADMIN");
        System.out.println(password.encode("123"));
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username as principal,password as credentials,active FROM users where username=?")
                .authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
                .passwordEncoder(password);*/
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/villes/**").permitAll()
                .antMatchers("/login","/webjars/**").permitAll()
                .antMatchers("/cinemas/**").permitAll()
                .antMatchers("/salles/**").permitAll()
                .antMatchers("/imageFilm/**").permitAll()
                .antMatchers("/seances/**").permitAll()
                .antMatchers("/projections/**").permitAll()
                .antMatchers("/tickets/**").permitAll()
                .antMatchers("/payerTicket").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
