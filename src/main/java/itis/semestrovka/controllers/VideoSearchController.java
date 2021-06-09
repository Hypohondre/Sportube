package itis.semestrovka.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import itis.semestrovka.models.Video;
import itis.semestrovka.services.interfaces.SearchVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
public class VideoSearchController {
    private final SearchVideoService searchVideoService;

    @GetMapping("/search")
    public Page<Video> searchVideoByName(@PageableDefault(size = 1, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                         @RequestParam Optional<String> name) {
        return searchVideoService.getVideosByName(name, pageable);
    }
}
