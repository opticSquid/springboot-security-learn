package com.learn.springbootsecuritylearn.config;

import com.learn.springbootsecuritylearn.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    /**
     * Here in this method we are configuring the mechanism to be used for
     * authentication.
     * In this case we are using "basic authentication"
     * which is not recommended in production environments
     * except "/home" route all other routes are secured
     * Normally the application is CSRF protected so, non-browser
     * clients like Postman can not do post or put request
     * or basically no request is allowed which can alter or delete data
     * to disable CSRF you have to do it manually
     * CORS is also turned on, so you need to disable to CSRF because we are using HTTP not HTTPS
     */
   /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("normal_user")
                .antMatchers("/users/**").hasRole("admin_user")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin() // This activates form based login
                .loginPage("/signin") // This can be used to customize the signin url
                .loginProcessingUrl("/dologin")
                .and()
                .logout()
                .logoutUrl("/dologout") // requires a "post" request to this url to do logout
                .logoutSuccessUrl("/signin");

    }
    */

    /**
     * For spring version >2.5.5
     * use this function/ Bean
     */
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .authorizeRequests().antMatchers("/signin").permitAll().antMatchers("/public/**").hasRole("normal_user").antMatchers("/users/**").hasRole("admin_user").anyRequest().authenticated().and().httpBasic().and().formLogin() // This activates form based login
                .loginPage("/signin") // This can be used to customize the signin url
                .loginProcessingUrl("/dologin").and().logout().logoutUrl("/dologout") // requires a "post" request to this url to do logout
                .logoutSuccessUrl("/signin");
        http.authenticationProvider(this.daoAuthenticationProvider());
        return http.build();
    }

    /**
     * Here we are creating a user credential using which one can log in
     * We are using user details from the database here
     */
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
*/

    /**
     * For spring version >2.5.5
     * use this function / Bean
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(this.passwordEncoder());
        return provider;
    }

    /**
     * Configuring bcrypt password encryption
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
