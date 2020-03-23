package com.codecool.shop.config;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Objects;


@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up products and printing it
        String initDb = System.getProperty("db.init");
        if (Objects.equals(initDb, "true")){
            try {

                //setting up a new supplier
                Supplier vegeMan = new Supplier("VegMan", "Vegetables delivery");
                supplierDataStore.add(vegeMan);
                Supplier appleMan = new Supplier("AppleMan","Fruit delivery");
                supplierDataStore.add(appleMan);
                Supplier spiceMan = new Supplier("SpiceMan","Spice delivery");
                supplierDataStore.add(spiceMan);

                //setting up a new product category
                ProductCategory veg = new ProductCategory("Vegetable", "Food", "Healthy natural food");
                productCategoryDataStore.add(veg);
                ProductCategory fruit = new ProductCategory("Fruit","Food","Healthy natural food");
                productCategoryDataStore.add(fruit);
                ProductCategory spice = new ProductCategory("Spice","Food","Tasty spices");
                productCategoryDataStore.add(spice);

                productDataStore.add(new Product("Potato", 1.00f, "USD", "From Hungary", veg, vegeMan));
                productDataStore.add(new Product("Carrot", 0.20f, "USD", "From Hungary", veg, vegeMan));
                productDataStore.add(new Product("Onion", 0.30f, "USD", "From Hungary", veg, vegeMan));
                productDataStore.add(new Product("Red Lady Apple",0.50f,"USD","From Hungary",fruit,appleMan));
                productDataStore.add(new Product("Orange",0.70f,"USD","From Brasilia",fruit,appleMan));
                productDataStore.add(new Product("Banana",0.60f,"USD","From Brasilia",fruit,appleMan));
                productDataStore.add(new Product("Grape",0.50f,"USD","From Hungary",fruit,appleMan));
                productDataStore.add(new Product("Cinnamon",0.10f,"USD","From Madagascar",spice,spiceMan));
                productDataStore.add(new Product("Black pepper",0.10f,"USD","From India",spice,spiceMan));
                productDataStore.add(new Product("Rosemary",0.10f,"USD","From Italy",spice,spiceMan));
                productDataStore.add(new Product("Curry",0.10f,"USD","From India",spice,spiceMan));
                productDataStore.add(new Product("Chili",0.10f,"USD","From Mexico",spice,spiceMan));
                productDataStore.add(new Product("Sea salt",0.10f,"USD","From China",spice,spiceMan));

            } catch (DataSourceException e) {
                System.err.println("Could not initialize Data");
                e.printStackTrace();
                System.exit(1);
            }
        }


    }
}
