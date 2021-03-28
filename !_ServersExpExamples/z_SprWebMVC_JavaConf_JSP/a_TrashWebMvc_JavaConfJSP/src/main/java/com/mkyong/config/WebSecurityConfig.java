package com.mkyong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("nimda").roles("ADMIN");

        // form javaguides
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        // from javaguides com
//        auth.inMemoryAuthentication()
//                .withUser("ramesh").password("{noop}ramesh").roles("USER").and()
//                .withUser("admin")
//                .password("{noop}admin").credentialsExpired(true)
//                .accountExpired(true).accountLocked(true)
//                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
//                .roles("ADMIN");

        // from memoryNotFound com
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER")
//                .and()
//                .withUser("manager")
//                .password("password")
//                .credentialsExpired(true)
//                .accountExpired(true)
//                .accountLocked(true)
//                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
//                .roles("MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .authorizeRequests().antMatchers("/login**").permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();

        // from codejava com
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/admin**").access("hasRole('ADMIN')")
//                .and().formLogin();

        // from codejava com _ 2
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/WEB-INF/jsp/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/welcome")
//                .failureUrl("/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

        // from memoryNotFound com
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}