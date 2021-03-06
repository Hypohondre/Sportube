package itis.semestrovka.repositories;

import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByName(String name);
    Page<Playlist> findAllByUserEquals(User user, Pageable pageable);
}
