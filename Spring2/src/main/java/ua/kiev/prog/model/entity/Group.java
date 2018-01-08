package ua.kiev.prog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Groups")
@NoArgsConstructor
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy="group", cascade=CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<Contact>();

    public static class Builder {

        Group instance = new Group();

        public Group.Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Group build() {
            return instance;
        }
    }

//    @Override
//    public String toString() {
//        return "Group{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", contacts=" + contacts +
//                '}';
//    }
}
