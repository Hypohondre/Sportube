package itis.semestrovka.controllers.rest;

import com.auth0.jwt.JWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import itis.semestrovka.dto.PlaylistDto;
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

@Tag(name = "Плейлисты", description = "Взаимодействие с плейлистами")
@RestController
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService service;

    @Operation(
            summary = "получение всех плейлистов",
            description = "получение страницы плейлистов"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/playlists")
    public Page<Playlist> getAll(@PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @Operation(
            summary = "получение плейлиста",
            description = "получение объекта плейлиста"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/playlists/{id}")
    public PlaylistDto getPlaylist(@PathVariable Long id) {
        return service.getPlaylist(id);
    }

    @Operation(
            summary = "получение всех плейлистов пользователя",
            description = "получение страницы плейлистов пользователя"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/getAllPlaylistsByUser")
    public Page<Playlist> getAllByUser(@CookieValue Cookie token, @PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAllByUser(getIdFromCookie(token), pageable);
    }

    @Operation(
            summary = "добавление плейлиста",
            description = "добавление плейлиста"
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/playlists")
    public RedirectView addPlaylist(PlaylistForm form,
                                    @CookieValue Cookie token) {
        PlaylistDto playlist = service.addPlaylist(form, getIdFromCookie(token));
        return new RedirectView("/playlists/"+playlist.getId());
    }

    @Operation(
            summary = "редактирование плейлиста",
            description = "редактирование плейлиста"
    )
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/playlists/{id}")
    public RedirectView updatePlaylist(@PathVariable Long id ,PlaylistForm form, @CookieValue Cookie token) {
        service.updatePlaylist(id, form, getIdFromCookie(token));
        return new RedirectView("playlists/"+id);
    }

    @Operation(
            summary = "удаление плейлиста",
            description = "удаление плейлиста"
    )
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id,
                                            @CookieValue Cookie token) {
        service.deletePlaylist(id, getIdFromCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getIdFromCookie(Cookie token) {
        return  Long.parseLong(JWT.decode(token.getValue()).getSubject());
    }
}
