package model;

public class Order {
    private Integer number; //унікальне поле
    private String orderOwner; //хто замовив
    private Status state;


    enum Status {
        NEW,
        CONFIRMED
    }

    public Order(Integer number, String orderOwner, Status state) {
        this.number = number;
        this.orderOwner = orderOwner;
        this.state = state;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", orderOwner='" + orderOwner + '\'' +
                ", state=" + state +
                '}';
    }
}
