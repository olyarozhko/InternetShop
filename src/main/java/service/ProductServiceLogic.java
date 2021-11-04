package service;

import dao.ProductDaoImpl;
import model.Product;
import model.Response;

public class ProductServiceLogic implements ProductService {

    @Override
    public Response<Product> addProduct(String name, String firm, Integer price, Integer maxLoad, String loadType) {
        var productNew = new Product(name, firm, price, maxLoad, loadType);
        var productDao = new ProductDaoImpl();


        if (name == null || firm == null || price == null || maxLoad == null || loadType == null) {
            return new Response<>(null, false, "\nAll product data parameters should be not blank");
        }
        if (productDao.getByName(name) != null) {
            return new Response<>(null, false, "\nProduct with such name already exists.");
        }
        if (!loadType.equalsIgnoreCase("vertical")) {
            if (!loadType.equalsIgnoreCase("frontal")) {
                return new Response<>(null, false, "\nLoad type should be: 'Frontal' or 'Vertical'");
            }
        }

        productDao.addProduct(productNew);
        return new Response<>(productNew, true, null);
    }

    @Override
    public Response<Product> editProduct(Integer productToUpdateId, String nameNew, String firm, Integer price,
                                         Integer maxLoad, String loadType) {
        var productDao = new ProductDaoImpl();
        var product = new ProductDaoImpl().getById(productToUpdateId);

        if (productToUpdateId == null) {
            return new Response<>(null, false, "\nProduct ID can\t be blank");
        }

        if (product == null) {
            return new Response<>(null, false, "\nProduct with such ID doesn't exist.");
        }

        if (new ProductDaoImpl().getByName(nameNew) != null) {
            return new Response<>(null, false, "\nProduct with such name already exists.");
        }

        if(loadType != null) {
            if (!loadType.equalsIgnoreCase("vertical")) {
                if (!loadType.equalsIgnoreCase("frontal")) {
                    return new Response<>(null, false, "\nLoad type should be: 'Frontal' or 'Vertical'");
                }
            }
        }
        product.setLoadType(loadType);

        if(nameNew != null){
            product.setProductName(nameNew);
        }

        if(firm != null){
            product.setFirm(firm);
        }

        if(price != null){
            product.setPrice(price);
        }

        if(maxLoad != null){
            product.setMaxLoad(maxLoad);
        }

        if(loadType != null){
            product.setFirm(firm);
        }

        productDao.editProduct(productToUpdateId, product);
        return new Response<>(product, true, null);
    }

}
