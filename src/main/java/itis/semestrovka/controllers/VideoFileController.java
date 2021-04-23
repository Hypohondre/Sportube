package itis.semestrovka.controllers;

import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.Video;
import itis.semestrovka.services.interfaces.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class VideoFileController {
    private final VideoService service;

    @GetMapping("/videos/{page}")
    public Page<Video> getVideos(@PathVariable Optional<Integer> page) {
        return service.getVideos(page.orElse(0));
    }

    @PostMapping("/videos")
    public ResponseEntity<Video> addVideo(@RequestBody VideoForm form) {
        return new ResponseEntity<>(service.addVideo(form), HttpStatus.CREATED);
    }

    @PutMapping("/video/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id ,@RequestBody VideoForm form) {
        return new ResponseEntity<>(service.updateVideo(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
        service.deleteVideo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
