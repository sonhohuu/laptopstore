/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

/**
 *
 * @author admin
 */
public class Product {
    private String id;
    private String name;
    private String price;
    private int quantity;
    private String image;
    private String description;
    private String created_date;
    private Category category;


    public Product() {
    }

    public Product(Category category) {
        this.category = category;
    }

    public Product(String id, String name, String price, int quantity, String image, String description, String created_date, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.created_date = created_date;
        this.category = category;
    }

  
 

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

   

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tea{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", image=" + image + ", description=" + description + ", created_date=" + created_date + '}';
    }
    
    
}
