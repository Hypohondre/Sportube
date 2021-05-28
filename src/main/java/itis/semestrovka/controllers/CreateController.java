package itis.semestrovka.controllers;

import itis.semestrovka.dto.PlaylistDto;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.dto.forms.VideoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class CreateController {
    private RestTemplate template = new RestTemplate();
    private String url = "http://localhost:8080";

    @PostMapping("/create/user")
    public String createUser(SignUpForm form) {
        RequestEntity<SignUpForm> requestEntity = RequestEntity
                .post(url+"/users")
                .accept(MediaType.APPLICATION_JSON)
                .body(form);
        ResponseEntity<UserDto> user = template.exchange(requestEntity, UserDto.class);
        return "redirect:/profile";
    }

    @PostMapping("/create/video")
    public String createVideo(VideoForm form) {
        return null;
    }

    @PostMapping("/create/playlist")
    public String createPlaylist(PlaylistForm form) {
        RequestEntity<PlaylistForm> requestEntity = RequestEntity
                .post(url+"/playlists")
                .accept(MediaType.APPLICATION_JSON)
                .body(form);
        ResponseEntity<PlaylistDto> playlist = template.exchange(requestEntity, PlaylistDto.class);
        return "redirect:/playlist/"+playlist.getBody().getId();
    }
}
