package ua.kiev.prog.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Contacts")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude="id")
public class Contact {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    private String name;
    private String surname;
    private String phone;
    private String email;

}
