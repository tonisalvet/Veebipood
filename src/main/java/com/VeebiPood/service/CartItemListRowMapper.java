package com.VeebiPood.service;

import com.VeebiPood.service.gettersAndSetters.CartItemList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemListRowMapper implements RowMapper<CartItemList> {

    @Override
    public CartItemList mapRow(ResultSet resultSet, int i) throws SQLException {
        CartItemList cartItemList = new CartItemList();
        cartItemList.setId(resultSet.getLong("id"));
        cartItemList.setProductId(resultSet.getLong("product_id"));
        cartItemList.setAccountId(resultSet.getLong("account_id"));
        cartItemList.setQuantity(resultSet.getLong("quantity"));
        cartItemList.setPrice(resultSet.getBigDecimal("price"));
        cartItemList.setNameShort(resultSet.getString("name_short"));
        cartItemList.setTotalPrice(resultSet.getBigDecimal("total_price"));
        return cartItemList;
    }
}
