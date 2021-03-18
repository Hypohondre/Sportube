package itis.semestrovka.repositories;

import itis.semestrovka.models.UserCookie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCookieRepository extends JpaRepository<UserCookie, String> {
}
