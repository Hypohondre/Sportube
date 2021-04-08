package itis.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "birth", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String photo;

    private String code;

    public enum State {
        ACTIVE, BANNED, FULLACTIVE
    }

    public enum Role {
        USER, ADMIN
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<UserCookie> userCookies;


    public User(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return this.state == State.ACTIVE || this.state == State.FULLACTIVE;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

}
