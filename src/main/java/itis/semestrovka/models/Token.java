package itis.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Token {
    @Id
    @GeneratedValue
    private Long id;


    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
