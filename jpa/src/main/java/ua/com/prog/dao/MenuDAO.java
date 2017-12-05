package ua.com.prog.dao;

import ua.com.prog.entity.Menu;

import java.util.List;

public interface MenuDAO{

    void create(Menu menu);

    List<Menu> getMenus(long from, long to);

    List<Menu> getOnlyWithDiscount();

    List<Menu> getNotMore100500Kilo(List<Integer> list);
}
