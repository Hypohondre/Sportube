package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.dto.mappers.PlaylistFormMapper;
import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.PlaylistRepository;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository repository;
    private final PlaylistFormMapper mapper;
    private final UserRepository userRepository;

    @Override
    public Page<Playlist> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }



    @Override
    public Playlist getPlaylist(Long id) {
        return repository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    @SneakyThrows
    @Override
    public Page<Playlist> getAllByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
        return repository.findAllByUserEquals(user, pageable);
    }

    @SneakyThrows
    @Override
    public Playlist addPlaylist(PlaylistForm form, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));

        Playlist playlist = mapper.formToPlaylist(form);
        playlist.setUser(user);
        return repository.save(playlist);
    }

    @SneakyThrows
    @Override
    public Playlist updatePlaylist(Long id, PlaylistForm form, Long userId) {
        Playlist playlistForUpdate = repository.findById(id)
                .orElseThrow(IllegalStateException::new);

        if (playlistForUpdate.getUser().getId().equals(userId)) {
            playlistForUpdate.setName(form.getName());
            return repository.save(playlistForUpdate);
        } else throw new AccessDeniedException("you can not update this playlist");
    }

    @SneakyThrows
    @Override
    public void deletePlaylist(Long id, Long userId) {
        Playlist playlistForDelete = repository.findById(id)
                .orElseThrow(IllegalStateException::new);

        if (playlistForDelete.getUser().getId().equals(userId)) repository.delete(playlistForDelete);
        else throw new AccessDeniedException("you can not delete this video");
    }
}
