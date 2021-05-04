package itis.semestrovka.security.configure;

import itis.semestrovka.security.token.TokenAuthenticationFilter;
import itis.semestrovka.security.token.TokenAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenAuthenticationFilter filter;
    private final TokenAuthenticationProvider provider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().disable();
        http.addFilterBefore(filter, BasicAuthenticationFilter.class);
    }

    @SneakyThrows
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(provider);
    }
}