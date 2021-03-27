package itis.semestrovka.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;


@Configuration
@PropertySource(value = "classpath:/application.properties")
@RequiredArgsConstructor
public class ApplicationContextConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver(){
        return new AuthenticationPrincipalArgumentResolver();
    }


}
