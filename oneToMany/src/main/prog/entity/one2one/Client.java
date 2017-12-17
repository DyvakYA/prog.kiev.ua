package main.prog.entity.one2one;

import javax.persistence.*;

/**
 * Created by User on 12/9/2017.
 */
@Entity
@Table(name = "clients1")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

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

        public Builder setAddress(Address address) {
            instance.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
                '}';
    }
}
