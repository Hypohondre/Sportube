package itis.semestrovka.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preview;

    private String name;

    private String file;

    private Long size;

    private String description;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}