package model;

public class Order {
    private Integer number; //унікальне поле
    private String orderOwner; //хто замовив
    private Status state;


    enum Status {
        NEW,
        CONFIRMED
    }

}
