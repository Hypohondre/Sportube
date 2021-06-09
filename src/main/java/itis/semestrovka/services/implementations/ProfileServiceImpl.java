package itis.semestrovka.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository repository;

    @Override
    public UserDto getUser(String jwt) {
        DecodedJWT decodedJWT= JWT.decode(jwt);
        return getUserDto(decodedJWT);
    }

    private UserDto getUserDto(DecodedJWT decodedJWT) {
        return UserDto.builder()
                .id(Long.parseLong(decodedJWT.getSubject()))
                .email(decodedJWT.getClaim("email").asString())
                .username(decodedJWT.getClaim("username").asString())
                .birth(Date.valueOf(decodedJWT.getClaim("birth").asString()))
                .code(decodedJWT.getClaim("code").asString())
                .photo(decodedJWT.getClaim("photo").asString())
                .role(decodedJWT.getClaim("role").asString())
                .state(decodedJWT.getClaim("state").asString())
                .build();
    }

    @Override
    public void changePhoto(String photoPath, Long id) throws Throwable {
            User user = repository.findById(id)
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));

            user.setPhoto(photoPath);

            repository.save(user);
    }
}
