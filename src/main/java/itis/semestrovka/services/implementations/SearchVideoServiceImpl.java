package itis.semestrovka.services.implementations;

import itis.semestrovka.models.Video;
import itis.semestrovka.repositories.VideoRepository;
import itis.semestrovka.services.interfaces.SearchVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchVideoServiceImpl implements SearchVideoService {
    private final VideoRepository repository;

    @Override
    public Page<Video> getVideosByName(Optional<String> name, Pageable pageable) {
        return repository.findByName(name.orElse("_"), pageable);
    }
}
