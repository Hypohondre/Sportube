package itis.semestrovka.controllers.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.tags.Tag;
import itis.semestrovka.dto.VideoDto;
import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.Video;
import itis.semestrovka.services.interfaces.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

@Tag(name = "Видео", description = "Взаимодействие с видео файлами")
@RestController
@RequiredArgsConstructor
public class VideoFileController {
    private final VideoService service;
    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("permitAll()")
    @GetMapping("/videos")
    public Page<Video> getVideos(@PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getVideos(pageable);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/videosByPlaylist")
    public Page<Video> getAllVideosByPlaylist(@RequestParam Long id,
                                              @PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAllByPlaylist(id, pageable);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/videos/{id}")
    public VideoDto getVideo(@PathVariable Optional<Long> id) {
        return service.getVideo(id.orElseThrow(IllegalArgumentException::new));
    }

    @PreAuthorize("isAuthenticated()")
    @SneakyThrows
    @GetMapping(path = "/getVideo/{videoPath}", produces = "video/mp4")
    public FileSystemResource getVideo(@PathVariable String videoPath) {
        File file = new File(uploadPath + File.separator + videoPath);
        return new FileSystemResource(file);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/videos/{id}/{playlistId}")
    public RedirectView replaceVideo(@PathVariable Long id,
                                     @PathVariable Long playlistId,
                                     @CookieValue Cookie token) {
        if (service.replaceVideo(id, playlistId, getIdFromCookie(token)) == null) throw new IllegalStateException();
        return new RedirectView("/playlist?id=" + playlistId);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/userVideos/{userId}")
    public Page<Video> getAllUserVideos(@PathVariable Long userId,
                                        @PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAllUserVideos(userId, pageable);
    }


    @SneakyThrows(FileNotFoundException.class)
    @PostMapping("/videos")
    public RedirectView addVideo(VideoForm form,
                                 @CookieValue Cookie token,
                                 @RequestParam("preview") MultipartFile preview,
                                 @RequestParam("video") MultipartFile video) {
        if (preview == null || video == null) throw new FileNotFoundException();

        DecodedJWT jwt = JWT.decode(token.getValue());
        if (service.addVideo(form, jwt.getClaim("username").asString(), preview, video, getIdFromCookie(token)) == null) throw new IllegalStateException();
        return new RedirectView("/userPlaylists");
    }

    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/video/{id}")
    public RedirectView updateVideo(@PathVariable Long id,
                                    VideoForm form,
                                    @RequestParam("preview") MultipartFile preview,
                                    @CookieValue Cookie token) {
        if (preview == null) throw new FileNotFoundException();
        if (service.updateVideo(id, form, getIdFromCookie(token), preview) == null) throw new IllegalStateException();
        return new RedirectView("/video?id=" + id);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/video/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id,
                                         @CookieValue Cookie token) {
        service.deleteVideo(id, getIdFromCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getIdFromCookie(Cookie token) {
       return  Long.parseLong(JWT.decode(token.getValue()).getSubject());
    }
}
