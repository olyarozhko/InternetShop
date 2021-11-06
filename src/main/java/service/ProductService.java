package service;

import model.Product;
import model.Response;

import java.util.List;

/**
 * Created by Igor on 10/8/2019.
 */
public interface ProductService {

    Response<Product> addProduct(String name, String firm, Integer price, Integer maxLoad, String LoadType);

    Response<Product> editProduct(Integer productToUpdateId, String nameNew, String firm, Integer price, Integer maxLoad,
                                  String loadType);

    Response<List<Product>> addProductToOrder(String productIdStr);

   Response<List<Product>> searchProduct(String searchString);

}
