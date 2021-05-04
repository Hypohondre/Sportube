package itis.semestrovka.dto.mappers;

import itis.semestrovka.dto.forms.PlaylistForm;
import itis.semestrovka.models.Playlist;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlaylistFormMapper {
    @Mappings({
            @Mapping(target = "name", source = "form.name"),
    })
    Playlist formToPlaylist(PlaylistForm form);

    @InheritInverseConfiguration
    PlaylistForm playlistToForm(Playlist playlist);
}
