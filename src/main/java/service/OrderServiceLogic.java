package service;

import dao.OrderDaoImpl;
import model.Order;
import model.Product;
import model.Response;

import java.util.List;

public class OrderServiceLogic implements OrderService {
    @Override
    public Response<Order> createOrder(String username, List<Product> productList) {
        var orderNew = new Order(username, productList);
        var orderDao = new OrderDaoImpl();

        if (productList.size() == 0) {
            return new Response<>(null, false, "Product list is blank.");
        }
        orderDao.save(orderNew);
        return new Response<>(orderNew, true, null);
    }

    @Override
    public Response<Order> confirmOrder(Integer orderId) {
        var orderDao = new OrderDaoImpl();

        if (!orderIdChecking(orderId).isSuccess()) {
            return orderIdChecking(orderId);
        }

        if (orderDao.getById(orderId).getState() != Order.Status.NEW) {
            return new Response<>(null, false, "Impossible confirm Order with State - " +
                    orderDao.getById(orderId).getState());
        }
        orderDao.changeOrderStatus(orderId, Order.Status.CONFIRMED);
        return new Response<>(orderDao.getById(orderId), true, null);
    }

    @Override
    public Response<Order> cancelOrder(Integer orderId) {
        var orderDao = new OrderDaoImpl();

        if (!orderIdChecking(orderId).isSuccess()) {
            return orderIdChecking(orderId);
        }

        if (orderDao.getById(orderId).getState() == Order.Status.CANCELLED) {
            return new Response<>(null, false, "The Order is already cancelled.");
        }
        orderDao.changeOrderStatus(orderId, Order.Status.CANCELLED);
        return new Response<>(orderDao.getById(orderId), true, null);

    }

    @Override
    public Response<Order> orderIdChecking(Integer orderId) {
        var orderDao = new OrderDaoImpl();

        if (orderId == null) {
            return new Response<>(null, false, "Order ID can't be blank.");
        }

        if (orderDao.getById(orderId) == null) {
            return new Response<>(null, false, "Order with such ID doesn't exist.");
        }

        return new Response<>(null, true, null);
    }
}
