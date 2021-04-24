package itis.semestrovka.dto.mappers;

import itis.semestrovka.dto.forms.VideoForm;
import itis.semestrovka.models.Video;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VideoFormMapper {

    @Mappings({
            @Mapping(target = "preview", source = "form.preview"),
            @Mapping(target = "name", source = "form.name"),
            @Mapping(target = "size", source = "form.size"),
            @Mapping(target = "description", source = "form.description")
    })
    Video formToVideo(VideoForm form);

    @InheritInverseConfiguration
    VideoForm videoToForm(Video video);
}
