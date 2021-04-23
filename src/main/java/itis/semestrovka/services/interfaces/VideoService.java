package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.Video;
import org.springframework.data.domain.Page;

public interface VideoService {
    Page<Video> getVideos(Integer page);

    Video addVideo(VideoForm form);

    Video updateVideo(Long id, VideoForm form);

    void deleteVideo(Long id);
}
