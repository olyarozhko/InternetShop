import dao.OrderDaoImpl;
import dao.ProductDaoImpl;

import dao.UserDaoImpl;
import view.impl.LoginMenu;

public class Main {
    public static void main(String[] args) {
        new LoginMenu().show();
        UserDaoImpl.readFromCsv("resources/userdbFile.csv");
        ProductDaoImpl.readFromCsv("resources/productdbFile.csv");
        OrderDaoImpl.readFromCsv("resources/orderdbFile.csv");
    }

    LoginMenu loginMenu = new LoginMenu();
}
