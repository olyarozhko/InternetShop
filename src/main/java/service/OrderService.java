package service;

import model.Order;
import model.Product;
import model.Response;

import java.util.List;

public interface OrderService {
    Response<Order> createOrder(String username, List<Product> productList);

    Response<Order> confirmOrder(Integer orderId);

    Response<Order> cancelOrder(Integer orderId);

    Response<Order> orderIdChecking(Integer orderId);
}
