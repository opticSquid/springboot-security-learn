package com.learn.springbootsecuritylearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Here in this method we are configuring the mechanism to be used for
     * authentication.
     * In this case we are using "basic authentication"
     * which is not recommended in production environments
     * except "/home" route all other routes are secured
     * 
     * Normally the application is CSRF protected so, non-browser
     * clients like Postman can not do post or put request
     * or basically no request is allowed which can alter or delete data
     * to disable CSRF you have to do it manually
     * 
     * CORS is also turned on, so you need to disable to
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").hasRole("normal_user")
                .antMatchers("/users/**").hasRole("admin_user")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    /**
     * Here we are creating a user credential using which one can log in
     * We are using a in memory authentication here which is not suitable
     * for production environments
     * We are hard coding user credentials here
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("soumalya")
                .password(this.passwordEncoder().encode("1234"))
                .roles("normal_user");
        // We can create multiple users by creating a seperate instance of
        // "auth" with different username and password
        auth
                .inMemoryAuthentication()
                .withUser("chetna")
                .password("5678")
                .roles("admin_user");
    }

    /**
     * Configuring bcrypt password encryption
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
