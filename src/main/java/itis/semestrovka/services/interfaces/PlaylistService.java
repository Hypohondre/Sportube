package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlaylistService {
    Page<Playlist> getAll(Pageable pageable);

    Playlist getPlaylist(Long id);

    Playlist addPlaylist(PlaylistForm form, Long id);

    Playlist updatePlaylist(Long id, PlaylistForm form, Long userId);

    void deletePlaylist(Long id, Long userId);
}
