package itis.semestrovka.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.Video;
import itis.semestrovka.security.details.UserDetailsImpl;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

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
    public ResponseEntity<Video> getVideo(@PathVariable Optional<Long> id) {
        return new ResponseEntity<>(service.getVideo(id
                .orElseThrow(IllegalArgumentException::new)), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(path = "/getVideo/{videoPath}", produces = "video/mp4")
    public FileSystemResource getVideo(@PathVariable String videoPath) {
        File file = new File(uploadPath + File.separator + videoPath);
        return new FileSystemResource(file);
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

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/video/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id ,@RequestBody VideoForm form, @CookieValue Cookie token) {
        return new ResponseEntity<>(service.updateVideo(id, form, getIdFromCookie(token)), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/video/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id,
                                         @CookieValue Cookie token) {
        service.deleteVideo(id, getIdFromCookie(token));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getIdFromCookie(Cookie token) {
       return  Long.parseLong(JWT.decode(token.getValue()).getSubject());
    }
}
