package itis.semestrovka.security.configure;

import itis.semestrovka.security.token.TokenAuthenticationFilter;
import itis.semestrovka.security.token.TokenAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenAuthenticationFilter filter;
    private final TokenAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/signUp").permitAll()  // permitAll(), authentacited(), hasAuthority("ADMIN")
                .antMatchers("/signIn").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/static/**").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
}
