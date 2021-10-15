package view.impl;

import view.Menu;

public class ProductMenu implements Menu {

    @Override
    public void show() {

    }

    @Override
    public void exit() {
        new LoginMenu().show();
    }
}
