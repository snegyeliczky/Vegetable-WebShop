package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private Map<String, List<Product>> cart = new HashMap<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(String user, Product product) {

        if (!cart.containsKey(user)) {
            cart.put(user, new ArrayList<>());
        }
        cart.get(user).add(product);

    }

    @Override
    public void remove(int id, String userName) {
        for (String user : cart.keySet()) {
            if (user.equals(userName)) {
                List<Product> cartList = cart.get(user);
                for (Product product : cartList) {
                    if (product.getId() == id) {
                        cartList.remove(product);
                        break;
                    }
                }
                cart.put(user, cartList);
            }
        }
    }

    @Override
    public List<Product> getUserProductList(String user) {
        if (cart.get(user) == null) {
            cart.put(user, new ArrayList<Product>());
        }
        return cart.get(user);
    }

    public Map<Product, Integer> getUserCartInMap(String user) {
        List<Product> userItems = getUserProductList(user);
        Map<Product, Integer> userItemMap = new HashMap<>();
        for (Product product : userItems) {
            int inMap = 0;
            for (Product productInMap : userItemMap.keySet()) {
                if (productInMap.getId() == product.getId()) {
                    userItemMap.put(productInMap, userItemMap.get(productInMap) + 1);
                    inMap = 1;
                }
            }
            if (inMap == 0) {
                userItemMap.put(product, 1);
            }
        }
        return userItemMap;
    }

}
