package model;

public class Admin extends User {
    private String sessionId;
    private int orderId;
    private int orderStatus;
    private String comments;
    private boolean notifyCustomer;
    private String updateOrder;


    public Admin(String username, String password, String firstName,
                 String lastName, String phoneNumber, boolean isLocked,
                 String sessionId, int orderId, int orderStatus, String comments,
                 boolean notifyCustomer, String updateOrder) {
        super(username, password, firstName, lastName, phoneNumber, isLocked);
        this.sessionId = sessionId;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.comments = comments;
        this.notifyCustomer = notifyCustomer;
        this.updateOrder = updateOrder;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public String getComments() {
        return comments;
    }

    public boolean isNotifyCustomer() {
        return notifyCustomer;
    }

    public String getUpdateOrder() {
        return updateOrder;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "sessionId='" + sessionId + '\'' +
                ", orderId=" + orderId +
                ", orderStatus=" + orderStatus +
                ", comments='" + comments + '\'' +
                ", notifyCustomer=" + notifyCustomer +
                ", updateOrder='" + updateOrder + '\'' +
                '}';
    }
}
