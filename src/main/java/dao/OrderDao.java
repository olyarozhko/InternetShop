package dao;

import model.Order;

import java.util.List;

public interface OrderDao {

    void readFromCsv(String filename);

    void addOrder(Order orderNew);

    void save(List<Order> orderdb);

    List<Integer> getByOrderOwner(String orderOwner);

    Order getById(Integer id);

    List<Order> getAll();

    void changeOrderStatus(Integer Id, Order.Status state);


}