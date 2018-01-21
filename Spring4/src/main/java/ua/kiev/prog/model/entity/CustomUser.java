package ua.kiev.prog.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class CustomUser {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "not null")
    @Size(min = 2, max = 30)
    private String login;

    @NotNull(message = "not null")
    @Size(min = 5, max = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Valid
    private UserRole role;

    @NotNull(message = "Not null")
    @Email
    private String email;

    @NotNull(message = "Not null")
    private String phone;

}
