package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.dto.mappers.VideoFormMapper;
import itis.semestrovka.models.Video;
import itis.semestrovka.repositories.VideoRepository;
import itis.semestrovka.services.interfaces.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoFormMapper mapper;
    private final VideoRepository repository;


    @Override
    public Page<Video> getVideos(Integer page) {
        return null;
    }

    @Override
    public Video addVideo(VideoForm form) {
        Video video = mapper.formToVideo(form);
        video.setDuration(Date.valueOf(form.getDuration()));
        return repository.save(video);
    }

    @Override
    public Video updateVideo(Long id, VideoForm form) {
        Video videoForUpdate = repository.findById(id).orElseThrow(IllegalStateException::new);
        videoForUpdate.setDescription(form.getDescription());
        videoForUpdate.setName(form.getName());
        videoForUpdate.setDuration(Date.valueOf(form.getDuration()));
        videoForUpdate.setPreview(form.getPreview());

        repository.save(videoForUpdate);
        return repository.save(videoForUpdate);
    }

    @Override
    public void deleteVideo(Long id) {
        Video videoForDelete = repository.findById(id).orElseThrow(IllegalStateException::new);

        repository.delete(videoForDelete);
    }
}
