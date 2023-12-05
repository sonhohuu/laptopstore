/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;   

/**
 *
 * @author admin
 */
public class Cart {
    private int id;
    private int userId;
    private float totalPrice;
    private List<CartItem> items;
    private String notes;

    public Cart() {
    }

    public Cart(int id, int userId, float totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public Cart(int id, int userId, float totalPrice, String notes) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

    public Cart(int id, int userId, float totalPrice, List<CartItem> items, String notes) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.items = items;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {

        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItem getItemById(String id) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == null ? id == null : item.getProduct().getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private boolean checkExist(String id) {
        if (items.stream().anyMatch(item -> (item.getProduct().getId() == null ? id == null : item.getProduct().getId().equals(id)))) {
            return true;
        }
        return false;
    }

    public void addItem(CartItem newItem) {
        if (checkExist(newItem.getProduct().getId())) {
            CartItem olditem = getItemById(newItem.getProduct().getId());
            olditem.setQuantity(olditem.getQuantity() + newItem.getQuantity());
        } else {
            items.add(newItem);
        }
    }
    public void removeItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney() {
        double t = 0;
        for (CartItem i : items) {
            t += (i.getQuantity() * Integer.parseInt(i.getProduct().getPrice()));
        }
        return t;
    }
}
