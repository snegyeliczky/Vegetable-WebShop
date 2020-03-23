package com.codecool.shop.dao.JDBCImplementation;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductJDBCTest {
    ProductJDBC productJDBC = new ProductJDBC();
    Product product;
    Supplier supplier;
    ProductCategory productCategory;

    private void creatProduct(){
        supplier = Mockito.mock(Supplier.class);
        Mockito.when(supplier.getId()).thenReturn(1);

        productCategory = Mockito.mock(ProductCategory.class);
        Mockito.when(productCategory.getId()).thenReturn(1);


        product = Mockito.mock(Product.class);
        product.setSupplier(supplier);
        Mockito.when(product.getName()).thenReturn("JDBCTestProduct");
        Mockito.when(product.getDescription()).thenReturn("Test product for test");
        Mockito.when(product.getDefaultPrice()).thenReturn(0.111f);
        Mockito.when(product.getSupplier()).thenReturn(supplier);
        Mockito.when(product.getProductCategory()).thenReturn(productCategory);
    }


    @Test
    void add() throws DataSourceException {
        creatProduct();
        productJDBC.add(product);
        System.out.println("product added");
        Product testProduct = productJDBC.find(14);
        assertEquals(product.getName(),testProduct.getName());


    }

    @Test
    void find() {

    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getBy() {
    }

    @Test
    void testGetBy() {
    }
}