package ua.kiev.prog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Contacts")
@NoArgsConstructor
@Getter
@Setter
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

    public static class Builder {

        Contact instance = new Contact();

        public Builder setGroup(Group group) {
            instance.group = group;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            instance.surname = surname;
            return this;
        }

        public Builder setPhone(String phone) {
            instance.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            instance.email = email;
            return this;
        }

        public Contact build() {
            return instance;
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
