package model;


public class Product {
    private String productName;
    private String firm;
    private Integer price;
    private Integer maxLoad;
    private String LoadType;

    public Product(String productName, String firm, Integer price, Integer maxLoad, String loadType) {
        this.productName = productName;
        this.firm = firm;
        this.price = price;
        this.maxLoad = maxLoad;
        LoadType = loadType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Integer maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getLoadType() {
        return LoadType;
    }

    public void setLoadType(String loadType) {
        LoadType = loadType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", firm='" + firm + '\'' +
                ", price=" + price +
                ", maxLoad=" + maxLoad +
                ", LoadType='" + LoadType + '\'' +
                '}';
    }
}
