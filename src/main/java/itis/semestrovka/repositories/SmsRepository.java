package itis.semestrovka.repositories;

import itis.semestrovka.models.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsRepository extends JpaRepository<Sms, Long> {
    Optional<Sms> findByText(String text);
}
