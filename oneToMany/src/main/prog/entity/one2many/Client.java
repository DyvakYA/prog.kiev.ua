package main.prog.entity.one2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/9/2017.
 */
@Entity
@Table(name = "clients2")
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
        @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name")
})
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @Transient
    private String comment = "Client";

    public static class Builder {

        Client instance = new Client();

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setAge(int age) {
            instance.age = age;
            return this;
        }

        public Builder setAddresses(List<Address> addresses) {
            instance.addresses = addresses;
            return this;
        }

        public Builder setComment(String comment) {
            instance.comment = comment;
            return this;
        }

        public Client build() {
            return instance;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
            address.setClient(this);
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addresses=" + addresses +
                ", comment='" + comment + '\'' +
                '}';
    }
}
