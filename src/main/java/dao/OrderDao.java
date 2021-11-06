package dao;

import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {


    void save(Order order);

    void changeOrderStatus(Integer orderId, Order.Status status);

    List<Order> getByUser(String username);

    Order getById(Integer id);

    List<Order> getAllOrders();

}
