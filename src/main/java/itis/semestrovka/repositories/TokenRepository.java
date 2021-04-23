package itis.semestrovka.repositories;

import itis.semestrovka.models.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByValue(String value);
}
