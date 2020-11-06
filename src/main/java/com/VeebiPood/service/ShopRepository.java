package com.VeebiPood.service;

import com.VeebiPood.service.Dropdowns.Category;
import com.VeebiPood.service.Dropdowns.CategoryRowMapper;
import com.VeebiPood.service.gettersAndSetters.CartItemList;

import com.VeebiPood.service.gettersAndSetters.Product;
import com.VeebiPood.service.gettersAndSetters.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShopRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void insertProduct(Product product) {
        String sql = "INSERT INTO product (serial, name_short, name_long, category_id, colour, size, gender, brand, quantity, price) " +
                "VALUES (:serial, :name_short, :name_long, :category_id, :colour, :size, :gender, :brand, :quantity, :price)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("serial", product.getSerial());
        paramMap.put("name_short", product.getNameShort());
        paramMap.put("name_long", product.getNameLong());
        paramMap.put("category_id", product.getCategoryId());
        paramMap.put("colour", product.getColour());
        paramMap.put("size", product.getSize());
        paramMap.put("gender", product.getGender());
        paramMap.put("brand", product.getBrand());
        paramMap.put("quantity", product.getQuantity());
        paramMap.put("price", product.getPrice());
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Product> getProductInfo() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new HashMap<>(), new ProductRowMapper());
    }

    public BigDecimal getProductPrice(Long id) {
        String sql = "SELECT price FROM product where id = :id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paraMap, BigDecimal.class);
    }

    public String getProductName(Long id) {
        String sql = "SELECT name_short FROM product where id = :id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paraMap, String.class);
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO category (name) VALUES (:name)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", category.getName());
        jdbcTemplate.update(sql, paraMap);
    }

    public List<Category> getCategory() {
        String sql = "SELECT id, name FROM category";
        return jdbcTemplate.query(sql, new HashMap<>(), new CategoryRowMapper());
    }


    public void addItemToCart(Long productId, Long accountId, Long quantity, BigDecimal price) {
        String sql = "INSERT INTO cart_item (product_id, account_id, quantity, price) " +
                "VALUES (:product_id, :account_id, :quantity, :price)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_id", productId);
        paramMap.put("account_id", accountId);
        paramMap.put("quantity", quantity);
        paramMap.put("price", price);
        jdbcTemplate.update(sql, paramMap);
    }



    public void removeItemFromCart(Long productId, Long accountId) {
        String sql = "DELETE FROM cart_item WHERE account_id = :account_id AND product_id = :product_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_id", productId);
        paramMap.put("account_id", accountId);
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Long> checkIfItemInCart(Long productId, Long accountId) {
        String sql = "SELECT quantity FROM cart_item WHERE product_id = :product_id and account_id = :account_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_id", productId);
        paramMap.put("account_id", accountId);
        return jdbcTemplate.queryForList(sql, paramMap, Long.class);
    }

    public void updateQuantity(Long productId, Long accountId, Long newQuantity) {
        String sql = "UPDATE cart_item SET quantity = :quantity WHERE account_id = :account_id and product_id = :product_id ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("product_id", productId);
        paramMap.put("account_id", accountId);
        paramMap.put("quantity", newQuantity);
        jdbcTemplate.update(sql, paramMap);
    }

    public Long getAccountId(String userName) {
        String sql = "SELECT id FROM account WHERE user_name = :user_name";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("user_name", userName);
        return jdbcTemplate.queryForObject(sql, paramMap, Long.class);
    }

//    public List<CartItemList> getCartItemList(Long accountId) {
//        String sql = "SELECT * FROM cart_item WHERE account_id = :account_id";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("account_id", accountId);
//        return jdbcTemplate.query(sql, paramMap, new CartItemListRowMapper());
//    }

    public List<CartItemList> getCartItemList(Long accountId) {
        String sql = "SELECT *, c.quantity * p.price total_price FROM cart_item c join product p on p.id = c.product_id\n" +
                "WHERE account_id = :account_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_id", accountId);
        return jdbcTemplate.query(sql, paramMap, new CartItemListRowMapper());
    }




//    public List<CartItem> getCartItemList(CartItem cartItem) {
//        String sql = "SELECT * FROM cart_item WHERE account_id = :account_id";
//        Map<String, Long> paramMap = ne
//        w HashMap<>();
//        paramMap.put("account_id", cartItem.getAccountId());
//        return jdbcTemplate.query(sql, new HashMap<>(), new CartItemRowMapper());
//    }

    //select * from product where gender = 'male'

    // TODO delete row from cart db- kui orderiks läheb.
    // TODO delete row from product db
    // TODO Print lists - product, cart_item. Listi järgi nupud kustutusfunktsiooniga.

/*

    public void createAccount(AddAccount addAccount) {
        String sql = "INSERT INTO bank (account_no, balance, client_id) VALUES (:account_no, :balance, :client_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", addAccount.getAccountNumber());
        paramMap.put("balance", addAccount.getBalance());
        paramMap.put("client_id", addAccount.getClientId());
        jdbcTemplate.update(sql, paramMap);



    public BigDecimal getBalance(String fromAccount) {
        String sql = "SELECT balance FROM bank WHERE account_no = :account_no";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account_no", fromAccount);
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    public void updateBalance(String fromAccount, BigDecimal amount) {
        String sql = "UPDATE bank SET balance = :balance WHERE account_no = :account_no";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("account_no", fromAccount);
        paramMap.put("balance", amount);
        jdbcTemplate.update(sql, paramMap);
    }


    public Long createClient(AddClient addClient) {
        String sql = "INSERT INTO clients (first_name, last_name) VALUES (:first_name, :last_name)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", addClient.getFirstName());
        paramMap.put("last_name", addClient.getLastName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

    public Long getAccountIdByAccountNumber(String account) {
        String sql = "SELECT id FROM bank WHERE account_no = :account_no";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("account_no", account);
        return jdbcTemplate.queryForObject(sql, paramMap, Long.class);
    }

    public void logDeposit(Long toAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (to_account, sum) VALUES (:to_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("to_account", toAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    public void logWithdraw(Long fromAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (from_account, sum) VALUES (:from_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("from_account", fromAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }

    public void logTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        String sql = "INSERT INTO transaction_history (from_account, to_account, sum) VALUES (:from_account, :to_account, :sum)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("from_account", fromAccountId);
        paramMap.put("to_account", toAccountId);
        paramMap.put("sum", amount);
        jdbcTemplate.update(sql, paramMap);
    }
*/

    // TODO delete account? client?
    // TODO print out account list(tabeli formaadis - NP kasuta loopi) - see jäi tegemata. Tõenäoliselt võiks ära teha sist poes ka vaja
}
