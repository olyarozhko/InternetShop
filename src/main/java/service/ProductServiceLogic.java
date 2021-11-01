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

        productDao.save(productNew);
        return new Response<>(productNew, true, null);
    }

    @Override
    public Response<Product> editProduct(Integer productToUpdateId, String nameNew, String firm, Integer price,
                                         Integer maxLoad, String loadType) {
        var productUpdated = new Product(nameNew, firm, price, maxLoad, loadType);
        var productDao = new ProductDaoImpl();
        var oldProduct = new ProductDaoImpl().getById(productToUpdateId);

        if (productToUpdateId == null) {
            return new Response<>(null, false, "\nProduct ID can\t be blank");
        }

        if (oldProduct == null) {
            return new Response<>(null, false, "\nProduct with such ID doesn't exist.");
        }

        if (new ProductDaoImpl().getByName(nameNew) != null) {
            return new Response<>(null, false, "\nProduct with such name already exists.");
        }

        if (!loadType.equalsIgnoreCase("vertical")) {
            if (!loadType.equalsIgnoreCase("frontal")) {
                return new Response<>(null, false, "\nLoad type should be: 'Frontal' or 'Vertical'");
            }
        }
        productDao.update(oldProduct, productUpdated);
        return new Response<>(productUpdated, true, null);
    }

}
