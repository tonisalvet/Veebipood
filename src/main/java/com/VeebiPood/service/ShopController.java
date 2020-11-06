package com.VeebiPood.service;


import com.VeebiPood.service.Dropdowns.Category;
import com.VeebiPood.service.gettersAndSetters.AddCartItemRequest;
import com.VeebiPood.service.gettersAndSetters.CartItemList;
import com.VeebiPood.service.gettersAndSetters.LoggedInResponse;
import com.VeebiPood.service.gettersAndSetters.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PutMapping("insertProduct")
    public void insertProduct(@RequestBody Product product) {
        shopService.insertProduct(product);
    }


    @GetMapping("getProductInfo")
    public List<Product> getProductInfo() {
        return shopService.getProductInfo();
    }

    @PutMapping("addCategory")
    public void addCategory(@RequestBody Category category) {
        shopService.addCategory(category);
    }

    @GetMapping("getCategory")
    public List<Category> getCategory() {
        return shopService.getCategory();
    }

    @GetMapping("userStatus")
    public LoggedInResponse userStatus(Principal principal) {
        return new LoggedInResponse(principal);
    }

    @GetMapping("getProductPrice")
    public BigDecimal getProductPrice(@RequestBody Long id) {
        return shopService.getProductPrice(id);
    }

    @PutMapping("addItemToCart")
    public void addItemToCart(@RequestBody AddCartItemRequest request, Principal principal) {
        shopService.addItemToCart(request.getProductId(), request.getQuantity(), principal.getName());
    }

    @PutMapping("removeItemFromCart")
    public List<CartItemList> removeItemFromCart(@RequestBody AddCartItemRequest request, Principal principal) {
        shopService.addItemToCart(request.getProductId(), request.getQuantity(), principal.getName());
        return shopService.getCartItemList(principal.getName());
    }

    @GetMapping("getCartItemList")
    public List<CartItemList> getCartItemList(Principal principal) {
        return shopService.getCartItemList(principal.getName());
    }

//    @PutMapping("emptyCartItemList")
//    public void emptyCartItemList

// TODO @GetMapping("getProductPic")
// TODO kategooria kuvamine korrektseks

}