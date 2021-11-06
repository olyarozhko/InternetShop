package service;

import dao.ProductDaoImpl;
import model.Product;
import model.Response;

import java.util.Arrays;
import java.util.List;

public class ProductServiceLogic implements ProductService {
    List<Product> productList;
    ProductDaoImpl productDao;

    @Override
    public Response<Product> addProduct(String name, String firm, Integer price, Integer maxLoad, String loadType) {

        if (name == null || firm == null || price == null || maxLoad == null || loadType == null) {
            return new Response<>(null, false, "\nAll product data parameters should be not blank");
        }
        var productNew = new Product(name, firm, price, maxLoad, loadType);

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

        if (productToUpdateId == null) {
            return new Response<>(null, false, "\nProduct ID can\t be blank");
        }

        var product = new ProductDaoImpl().getById(productToUpdateId);
        if (product == null) {
            return new Response<>(null, false, "\nProduct with such ID doesn't exist.");
        }

        if (new ProductDaoImpl().getByName(nameNew) != null) {
            return new Response<>(null, false, "\nProduct with such name already exists.");
        }

        if (loadType != null) {
            if (!loadType.equalsIgnoreCase("vertical")) {
                if (!loadType.equalsIgnoreCase("frontal")) {
                    return new Response<>(null, false, "\nLoad type should be: 'Frontal' or 'Vertical'");
                }
            }
        }
        product.setLoadType(loadType);

        if (nameNew != null) {
            product.setProductName(nameNew);
        }

        if (firm != null) {
            product.setFirm(firm);
        }

        if (price != null) {
            product.setPrice(price);
        }

        if (maxLoad != null) {
            product.setMaxLoad(maxLoad);
        }

        if (loadType != null) {
            product.setFirm(firm);
        }

        productDao.editProduct(productToUpdateId, product);
        return new Response<>(product, true, null);
    }

    @Override
    public Response<List<Product>> addProductToOrder(String productIdStr) {

        if (productIdStr.length() == 0) {
            return new Response<>(null, false, "\nNo Ids are entered");
        }
        String[] productId = productIdStr.replaceAll("\\s+", "").split(",");

        Integer[] productIdInt = Arrays.stream(productId).map(Integer::valueOf).toArray(Integer[]::new);

        Arrays.stream(productIdInt).filter(id -> productDao.getById(id) != null)
                .forEach(id -> productList.add(productDao.getById(id)));

        if (productList.size() == 0) {
            return new Response<>(null, false, "\nNo Products with such Ids are found.");
        }

        return new Response<List<Product>>(productList, true, null);
    }

    @Override
    public Response<List<Product>> searchProduct(String searchString) {

        String finalSearchString = searchString.replaceAll("\\s+", "");

        if (searchString.length() == 0) {
            return new Response<>(null, false, "\nSearch String is blank.");
        }

        productDao.getAll().stream().filter(product -> product.getProductName().contains(finalSearchString) ||
                        product.getFirm().contains(finalSearchString) || product.getLoadType().contains(finalSearchString))
                .forEach(productList::add);

        if (productList.size() == 0) {
            return new Response<>(null, false, "\nNo Products found.");

        }

        return new Response<List<Product>>(productList, true, null);
    }
}
