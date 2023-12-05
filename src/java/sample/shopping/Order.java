
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Date;
import sample.user.User;

/**
 *
 * @author chi
 */
public class Order {
    private int id;
    private User user;
    private String total; 
    private String orderDate;
    private String notes;

    public Order() {
    }

    public Order(int id, User user, String total) {
        this.id = id;
        this.user = user;
        this.total = total;
    }

    public Order(int id, User user, String orderDate,  String total, String notes) {
        this.id = id;
        this.user = user;
        this.total = total;
        this.orderDate = orderDate;
        this.notes = notes;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
}
