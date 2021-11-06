package dao;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    static List<Order> orderList = new ArrayList<>();

    @Override
    public void save(Order order) {
        orderList.add(order);

    }

    @Override
    public void changeOrderStatus(Integer orderId, Order.Status status) {

    }

    @Override
    public List<Order> getByUser(String username) {
        return null;
    }

    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }
}
