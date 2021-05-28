package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.VideoDto;
import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.dto.mappers.VideoFormMapper;
import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import itis.semestrovka.models.Video;
import itis.semestrovka.repositories.PlaylistRepository;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.repositories.VideoRepository;
import itis.semestrovka.services.interfaces.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoFormMapper mapper;
    private final VideoRepository repository;
    private final PlaylistRepository playlistRepository;
    private final UploadingImgServiceImpl uploadingImgService;
    private final UserRepository userRepository;

    @Override
    public Page<Video> getVideos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Video> getAllByPlaylist(Long id, Pageable pageable) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
        return repository.findAllByPlaylistEquals(playlist,pageable);
    }

    @SneakyThrows
    @Override
    public VideoDto addVideo(VideoForm form, String username, MultipartFile preview, MultipartFile videoFile, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));

        Video video = mapper.formToVideo(form);
        String videoPath = uploadingImgService.upload(videoFile);
        String previewPath = uploadingImgService.upload(preview);

        video.setPreview(previewPath);
        video.setFile(videoPath);
        video.setCreator(user);
        video.setSize(videoFile.getSize());

        Playlist playlist = playlistRepository.findByName(username).orElseThrow(IllegalStateException::new);
        playlist.addVideo(video);

        Video newVideo = repository.save(video);
        return VideoDto.builder()
                .id(newVideo.getId())
                .name(newVideo.getName())
                .description(newVideo.getDescription())
                .file(newVideo.getFile())
                .creator(newVideo.getCreator())
                .preview(newVideo.getPreview())
                .size(newVideo.getSize())
                .build();
    }

    @Override
    public VideoDto replaceVideo(Long id, Long playlistId, Long userId) {
        Video video = repository.findById(id)
                .orElseThrow(IllegalStateException::new);

        if (video.getCreator().getId().equals(userId)) {
            Playlist originalPlaylist = video.getPlaylist();
            originalPlaylist.removeVideo(video);

            Playlist playlist = playlistRepository.findById(playlistId)
                    .orElseThrow(IllegalStateException::new);
            playlist.addVideo(video);
            return VideoDto.builder()
                    .id(video.getId())
                    .name(video.getName())
                    .description(video.getDescription())
                    .file(video.getFile())
                    .creator(video.getCreator())
                    .preview(video.getPreview())
                    .size(video.getSize())
                    .build();
        } else return null;
    }

    @SneakyThrows
    @Override
    public Page<Video> getAllUserVideos(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
        return repository.findAllByCreatorEquals(user, pageable);
    }

    @Override
    public VideoDto getVideo(Long id) {
        Video video = repository.findById(id).orElseThrow(IllegalStateException::new);
        return VideoDto.builder()
                .id(video.getId())
                .name(video.getName())
                .description(video.getDescription())
                .file(video.getFile())
                .creator(video.getCreator())
                .preview(video.getPreview())
                .size(video.getSize())
                .build();
    }

    @Override
    public VideoDto updateVideo(Long id, VideoForm form, Long userId, MultipartFile preview) {
        Video videoForUpdate = repository.findById(id).orElseThrow(IllegalStateException::new);
        if (videoForUpdate.getCreator().getId().equals(userId)) {
            videoForUpdate.setDescription(form.getDescription());
            videoForUpdate.setName(form.getName());
            String previewPath = uploadingImgService.upload(preview);
            videoForUpdate.setPreview(previewPath);

            Video video = repository.save(videoForUpdate);
            return VideoDto.builder()
                    .id(video.getId())
                    .name(video.getName())
                    .description(video.getDescription())
                    .file(video.getFile())
                    .creator(video.getCreator())
                    .preview(video.getPreview())
                    .size(video.getSize())
                    .build();
        } else {
            throw new AccessDeniedException("you can not update this video");
        }
    }

    @SneakyThrows
    @Override
    public void deleteVideo(Long id, Long userId) {
        Video videoForDelete = repository.findById(id).orElseThrow(IllegalStateException::new);

        if (videoForDelete.getCreator().getId().equals(userId)) repository.delete(videoForDelete);
        else throw new AccessDeniedException("you can not delete this video");
    }
}
