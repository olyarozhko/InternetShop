package model;


import java.util.Objects;

public class Product {
    private String productName;
    private String firm;
    private Integer price;
    private Integer maxLoad;
    private LoadType loadType;
    private Integer productId = 0;
    private static Integer productIdNext = 0;

    enum LoadType {
        VERTICAL,
        FRONTAL;
    }

    public Product() {
    }

    public Product(String productName, String firm, Integer price, Integer maxLoad, String loadType) {
        this.productId = productIdNext++;
        this.productName = productName;
        this.firm = firm;
        this.price = price;
        this.maxLoad = maxLoad;
        this.loadType = toLoadType(loadType);

    }

    private LoadType toLoadType(String loadTypeString) {
        LoadType loadType = null;
        if (loadTypeString.equalsIgnoreCase("vertical")) {
            loadType = LoadType.VERTICAL;
        }
        if (loadTypeString.equalsIgnoreCase("frontal")) {
            loadType = LoadType.FRONTAL;
        }
        return loadType;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setMaxLoad(Integer maxLoad) {
        this.maxLoad = maxLoad;
    }

    public void setLoadType(String loadType) {
        this.loadType = toLoadType(loadType);
    }

    public String getProductName() {
        return productName;
    }

    public String getFirm() {
        return firm;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getMaxLoad() {
        return maxLoad;
    }

    public String getLoadType() {
        return loadType.toString();
    }

    public Integer getProductId() {
        return productId;
    }


    @Override
    public String toString() {
        return
                "ID='" + productId + '\'' +
                "productName='" + productName + '\'' +
                ", firm='" + firm + '\'' +
                ", price=" + price +
                ", maxLoad=" + maxLoad +
                ", LoadType='" + loadType + '\'' +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getProductName().equals(product.getProductName()) && getFirm().equals(product.getFirm()) &&
                getPrice().equals(product.getPrice()) && getMaxLoad().equals(product.getMaxLoad()) &&
                getLoadType().equals(product.getLoadType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getFirm(), getPrice(), getMaxLoad(), getLoadType());
    }
}
