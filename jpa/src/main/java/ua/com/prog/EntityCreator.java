package ua.com.prog;

import ua.com.prog.entity.Menu;

/**
 * Created by User on 12/5/2017.
 */
public class EntityCreator {

    private Menu menu1 = new Menu.Builder()
            .setName("Soup")
            .setPrice(12000)
            .setWeight(200.00)
            .setDiscount(false)
            .build();

    private Menu menu2 = new Menu.Builder()
            .setName("Potatoes")
            .setPrice(100500)
            .setDiscount(true)
            .setWeight(100500)
            .build();

    private Menu menu3 = new Menu.Builder()
            .setName("Grecha")
            .setPrice(10000)
            .setDiscount(true)
            .setWeight(10500)
            .build();

    private Menu menu4 = new Menu.Builder()
            .setName("Manka")
            .setPrice(500)
            .setDiscount(true)
            .setWeight(100)
            .build();

    private Menu menu5 = new Menu.Builder()
            .setName("Pirog")
            .setPrice(102000)
            .setDiscount(true)
            .setWeight(101500)
            .build();

    public Menu getMenu1() {
        return menu1;
    }

    public Menu getMenu2() {
        return menu2;
    }

    public Menu getMenu3() {
        return menu3;
    }

    public Menu getMenu4() {
        return menu4;
    }

    public Menu getMenu5() {
        return menu5;
    }
}
