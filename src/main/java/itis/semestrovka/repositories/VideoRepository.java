package itis.semestrovka.repositories;

import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByPlaylist(Playlist playlist, Pageable pageable);
    Page<Video> findAllByPlaylistEquals(Playlist playlist, Pageable pageable);
}
