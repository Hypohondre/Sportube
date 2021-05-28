package itis.semestrovka.controllers;

import itis.semestrovka.dto.PlaylistDto;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.VideoDto;
import itis.semestrovka.security.SecurityHelper;
import itis.semestrovka.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@Controller
@RequiredArgsConstructor
public class PageController {
    private final SecurityHelper helper;
    private final RestTemplate template;
    @Value("${home.url}")
    private String url;

    @PreAuthorize("permitAll()")
    @GetMapping("/lenta")
    public String getLentaPage() {
        return "users_view_page";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {return "sign_in_page";}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        UserDetailsImpl userDetails = helper.getSecurityPrincipal();
        ResponseEntity<UserDto> user = template.getForEntity(url + "/users/" + userDetails.getUser().getId(), UserDto.class);
        model.addAttribute("user", user.getBody());
        return "profile_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editProfile")
    public String getEditProfilePage(Model model) {
        UserDetailsImpl userDetails = helper.getSecurityPrincipal();
        ResponseEntity<UserDto> userForUpdate = template.getForEntity(url + "/users/" + userDetails.getUser().getId(), UserDto.class);
        model.addAttribute("user", userForUpdate.getBody());
        return "profile_edit_page";
    }

    @GetMapping("/userPlaylists")
    public String getUserPlaylistsPage() {
        return "user_playlists_page";
    }

    @GetMapping("/createPlaylist")
    public String getCreatePlaylistPage() {
        return "create_playlist";
    }

    @GetMapping("/playlist/{id}")
    public String getPlaylistPage(@PathVariable Long id, Model model) {
        ResponseEntity<PlaylistDto> playlist = template.getForEntity(url + "/playlists/" + id, PlaylistDto.class);
        model.addAttribute("playlist", playlist.getBody());
        return "playlist_page";
    }

    @GetMapping("/createVideo")
    public String getCreateVideoPage() {
        return "create_video";
    }

    @GetMapping("/video/{id}")
    public String getVideoPage(@PathVariable Long id, Model model) {
        ResponseEntity<VideoDto> video = template.getForEntity(url+"/videos/"+id, VideoDto.class);
        model.addAttribute("video", video.getBody());
        return "video_page";
    }

    @GetMapping("/editPlaylist/{id}")
    public String getPlaylistEditPage(Model model, @PathVariable Long id) {
        ResponseEntity<PlaylistDto> playlistDto = template.getForEntity(url + "/playlists/" + id, PlaylistDto.class);
        model.addAttribute("playlist", playlistDto.getBody());
        return "edit_playlist";
    }

    @GetMapping("/editVideo/{id}")
    public String getVideoEditPage(@PathVariable Long id, Model model) {
        ResponseEntity<VideoDto> video = template.getForEntity(url + "/videos/" + id, VideoDto.class);
        model.addAttribute("video", video.getBody());
        return "edit_video";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/user/{id}")
    public String getUserProfile(@PathVariable Long id,
                                 Model model) {
        ResponseEntity<UserDto> user = template.getForEntity(url + "/users/" + id, UserDto.class);
        model.addAttribute("user", user.getBody());
        return "userProfilePage";
    }
}
