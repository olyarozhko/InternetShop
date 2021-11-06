package model;

import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id = 0; //унікальне поле
    private static Integer idNext = 0;
    private String orderOwner; //хто замовив
    private Status state;
    private List<Product> productList;
    private Double sum;

    public enum Status {
        NEW,
        CONFIRMED,
        CANCELLED
    }


    public Order(String orderOwner, List<Product> productList) {
        this.orderOwner = orderOwner;
        this.productList = productList;
        this.id = idNext ++;
        this.state = Status.NEW;
        this.sum = productList.stream().mapToDouble(Product::getPrice).sum();
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public static Integer getIdNext() {
        return idNext;
    }

    public String getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Double getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return  "ID=" + id +
                ", orderOwner='" + orderOwner + '\'' +
                ", state=" + state +
                ", productList:" +
                "\n" +
                            "\n" + productList + "," +
                "\n" +
                "\n--------------" +
                "\n\t SUM=" + sum +
                "\n"+
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId()) && getOrderOwner().equals(order.getOrderOwner()) &&
                getState() == order.getState() && getProductList().equals(order.getProductList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderOwner(), getState(), getProductList());
    }
}
