package itis.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoForm {
    private String preview;

    private String name;

    private Double size;

    private LocalDate duration;

    private String description;
}
