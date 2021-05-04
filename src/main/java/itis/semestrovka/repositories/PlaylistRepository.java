package itis.semestrovka.repositories;

import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByName(String name);
}
