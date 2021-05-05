package itis.semestrovka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    @PreAuthorize("permitAll()")
    @GetMapping("/lenta")
    public String getLentaPage() {
        return "users_view_page";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {return "sign_in_page";}

//    @GetMapping()
//    public String getPage() {return "";}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage() {return "profile_page";}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editProfile")
    public String getEditProfilePage() {
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

    @GetMapping("/playlist")
    public String getPlaylistPage() {
        return "playlist_page";
    }

    @GetMapping("/createVideo")
    public String getCreateVideoPage() {
        return "create_video";
    }

    @GetMapping("/video")
    public String getVideoPage() {
        return "video_page";
    }

    @GetMapping("/editPlaylist")
    public String getPlaylistEditPage() {
        return "edit_playlist";
    }

    @GetMapping("/editVideo")
    public String getVideoEditPage() {
        return "edit_video";
    }
}
