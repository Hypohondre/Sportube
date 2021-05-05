package itis.semestrovka.controllers;

import com.auth0.jwt.JWT;
import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.models.Playlist;
import itis.semestrovka.services.interfaces.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/playlists")
    public Page<Playlist> getAll(@PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/playlists/{id}")
    public Playlist getPlaylist(@PathVariable Long id) {
        return service.getPlaylist(id);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getAllPlaylistsByUser")
    public Page<Playlist> getAllByUser(@CookieValue Cookie token, @PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAllByUser(getIdFromCookie(token), pageable);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/playlists")
    public RedirectView addPlaylist(PlaylistForm form,
                                    @CookieValue Cookie token) {
        if (service.addPlaylist(form, getIdFromCookie(token)) == null) throw new IllegalStateException();
        return new RedirectView("/userPlaylists");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/playlists/{id}")
    public RedirectView updatePlaylist(@PathVariable Long id ,PlaylistForm form, @CookieValue Cookie token) {
        if (service.updatePlaylist(id, form, getIdFromCookie(token)) == null) throw new IllegalStateException();
        return new RedirectView("/userPlaylists");
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id,
                                            @CookieValue Cookie token) {
        service.deletePlaylist(id, getIdFromCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getIdFromCookie(Cookie token) {
        return  Long.parseLong(JWT.decode(token.getValue()).getSubject());
    }
}
