package itis.semestrovka.models;

import itis.semestrovka.dto.forms.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date birth;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        ACTIVE, BANNED
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

    public static User fromSignUpToUser(SignUpForm form) {
        return User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .username(form.getUsername())
                .birth(form.getBirth())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

}
