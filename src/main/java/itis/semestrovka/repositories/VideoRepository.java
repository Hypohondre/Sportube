package itis.semestrovka.repositories;

import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import itis.semestrovka.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByPlaylistEquals(Playlist playlist, Pageable pageable);
    Page<Video> findAllByCreatorEquals(User user, Pageable pageable);
}
