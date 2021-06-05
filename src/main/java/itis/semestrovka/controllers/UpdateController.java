package itis.semestrovka.controllers;

import itis.semestrovka.dto.PlaylistDto;
import itis.semestrovka.dto.VideoDto;
import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.dto.forms.VideoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class UpdateController {
    private final RestTemplate template;
    @Value("${home.url}")
    private String url;

    @PostMapping("/update/user/{id}")
    public String updateUser(@PathVariable Long id, SignUpForm form) {
        return "redirect:/logout";
    }

    @PostMapping("/update/video/{id}")
    public String updateVideo(@PathVariable Long id,
                              VideoForm form) {
        RequestEntity<VideoForm> requestEntity = RequestEntity
                .post(url+"/videos/"+id)
                .body(form);
        ResponseEntity<VideoDto> video = template.exchange(requestEntity, VideoDto.class);
        return "redirect:/video/" + video.getBody().getId();
    }

    @PostMapping("/update/playlist/{id}")
    public String updatePlaylist(@PathVariable Long id, PlaylistForm form) {
        RequestEntity<PlaylistForm> requestEntity = RequestEntity
                .post(url+"/playlists/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .body(form);
        ResponseEntity<PlaylistDto> playlist = template.exchange(requestEntity, PlaylistDto.class);
        return "redirect:/playlist/"+id;
    }
}
