package itis.semestrovka.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    @JsonBackReference
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Video> videos = new HashSet<>();

    public void addVideo(Video video) {
        this.videos.add(video);
        video.setPlaylist(this);
    }

    public void removeVideo(Video video) {
        this.videos.remove(video);
        video.setPlaylist(null);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
