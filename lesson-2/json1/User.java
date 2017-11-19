package lesson2.json1;

import java.util.Arrays;

/**
 * Created by User on 11/19/2017.
 */
public class User {

    String name;
    String surname;
    String[] phones;
    String[] sites;
    Address address;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", sites=" + Arrays.toString(sites) +
                ", address=" + address +
                '}';
    }
}
