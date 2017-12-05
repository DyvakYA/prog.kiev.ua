package ua.com.prog.entity;

import javax.persistence.*;

/**
 * Created by User on 12/5/2017.
 */

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private long price;
    @Column(name = "weight")
    private double weight;
    @Column(name = "discount")
    private boolean discount;

    public static class Builder {

        Menu instance = new Menu();

        public Builder setId(long id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setPrice(long price) {
            instance.price = price;
            return this;
        }

        public Builder setWeight(double weight) {
            instance.weight = weight;
            return this;
        }

        public Builder setDiscount(boolean discount) {
            instance.discount = discount;
            return this;
        }

        public Menu build() {
            return instance;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (price != menu.price) return false;
        if (Double.compare(menu.weight, weight) != 0) return false;
        if (discount != menu.discount) return false;
        return name != null ? name.equals(menu.name) : menu.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (discount ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }

}
