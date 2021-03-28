package com.config;

import com.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth
    .userDetailsService(userDetailsService)
    .passwordEncoder(getShaPasswordEncoder());
    
// from concretepage
    auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
    auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
    .disable()
    .authorizeRequests()
    .antMatchers("/resources/**", "/**").permitAll()
    .anyRequest().permitAll()
    .and();

    http.formLogin()
    .loginPage("/login")
    .loginProcessingUrl("/j_spring_security_check")
    .failureUrl("/login?error")
    .usernameParameter("j_username")
    .passwordParameter("j_password")
    .permitAll();

    http.logout()
    .permitAll()
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login?logout")
    .invalidateHttpSession(true);

// from concretepage
    http.authorizeRequests().antMatchers("/info/**").hasAnyRole("ADMIN", "USER").
             and().formLogin();
  }

  @Bean
  public PasswordEncoder getShaPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
