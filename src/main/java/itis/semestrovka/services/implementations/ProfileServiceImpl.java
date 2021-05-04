package itis.semestrovka.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.services.interfaces.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public UserDto getUser(String jwt) {
        DecodedJWT decodedJWT= JWT.decode(jwt);
        UserDto user = UserDto.builder()
                .id(Long.parseLong(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .username(decodedJWT.getClaim("username").asString())
                .birth(Date.valueOf(decodedJWT.getClaim("birth").asString()))
                .code(decodedJWT.getClaim("code").asString())
                .photo(decodedJWT.getClaim("photo").asString())
                .role(decodedJWT.getClaim("role").asString())
                .state(decodedJWT.getClaim("state").asString())
                .build();
        return user;
    }
}
