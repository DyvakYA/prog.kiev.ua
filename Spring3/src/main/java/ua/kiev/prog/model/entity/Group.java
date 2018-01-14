package ua.kiev.prog.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Groups")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "contacts")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy="group", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private  List<Contact> contacts = new ArrayList<>();

    @PreRemove
    public void nullableContacts(){
        contacts.forEach(c->c.setGroup(null));
    }
}
