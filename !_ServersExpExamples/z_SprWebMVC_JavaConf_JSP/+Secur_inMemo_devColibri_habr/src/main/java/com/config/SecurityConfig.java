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

//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(getShaPasswordEncoder());
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcryptPasswordEncoder());
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
//                .logoutSuccessUrl("/login?logout")
                .logoutSuccessUrl("/welcome")
                .invalidateHttpSession(true);

//        http.csrf();
    }

    // example _ for login use in Memory instead DataBase..

    /**
     * @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     * auth.inMemoryAuthentication()
     * .withUser("user")
     * .password("password")
     * .roles("USER")
     * .and()
     * .withUser("manager")
     * .password("password")
     * .credentialsExpired(true)
     * .accountExpired(true)
     * .accountLocked(true)
     * .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
     * .roles("MANAGER");
     * }
     */

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //    https://dzone.com/articles/password-encoder-migration-with-spring-security-5
//    @Bean
//    public PasswordEncoder getShaPasswordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    // https://stackoverflow.com/questions/49654143/spring-security-5-there-is-no-passwordencoder-mapped-for-the-id-null
//    @SuppressWarnings("deprecation")
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    public ShaPasswordEncoder getShaPasswordEncoder() {
////        return new ShaPasswordEncoder();
//    }

    //    https://www.baeldung.com/spring-security-5-password-storage
//    @Bean
//    public PasswordEncoder getShaPasswordEncoder() {
//        PasswordEncoder defaultEncoder = new StandardPasswordEncoder();
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//
//        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(
//                "bcrypt", encoders);
//        passworEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);
//
//        return passworEncoder;
//        return new BCryptPasswordEncoder();
//    }

}