package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.User;
import itis.semestrovka.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    Page<Video> getVideos(Pageable pageable);

    Page<Video> getAllByPlaylist(Long id, Pageable pageable);

    Video getVideo(Long id);

    Video replaceVideo(Long id, Long playlistId, Long userId);

    Page<Video> getAllUserVideos(Long userId, Pageable pageable);

    Video addVideo(VideoForm form, String username, MultipartFile preview, MultipartFile video, Long userId);

    Video updateVideo(Long id, VideoForm form, Long userId, MultipartFile preview);

    void deleteVideo(Long id, Long userId);
}
