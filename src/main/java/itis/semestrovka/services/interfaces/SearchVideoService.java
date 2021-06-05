package itis.semestrovka.services.interfaces;

import itis.semestrovka.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SearchVideoService {
    Page<Video> getVideosByName(Optional<String> name, Pageable pageable);
}
