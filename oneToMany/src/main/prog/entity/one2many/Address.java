package main.prog.entity.one2many;

import javax.persistence.*;

/**
 * Created by User on 12/17/2017.
 */
@Entity
@Table(name = "address2")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public static class Builder {

        Address instance = new Address();

        public Builder setCountry(String country) {
            instance.country = country;
            return this;
        }

        public Builder setCity(String city) {
            instance.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            instance.street = street;
            return this;
        }

        public Address build() {
            return instance;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", client=" + client +
                '}';
    }
}
