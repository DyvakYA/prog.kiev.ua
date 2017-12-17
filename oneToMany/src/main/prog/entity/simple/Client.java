package main.prog.entity.simple;

import javax.persistence.*;

/**
 * Created by User on 12/9/2017.
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
