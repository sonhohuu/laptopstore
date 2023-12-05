/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author chi
 */
public class ProductDAO {

    private static String GET_PRODUCT_BYID = "SELECT * FROM Product WHERE id = ?";
    private static String INSERT = "INSERT INTO [dbo].[Product]\n"
            + "           ([Name]\n"
            + "           ,[Price]\n"
            + "           ,[Stock]\n"
            + "           ,[Image]\n"
            + "           ,[Description]\n"
            + "           ,[Create_date]"
            + "          ,[category_id])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,Getdate(), ?)";
    private static String DELETE = "DELETE FROM [dbo].[Product]\n"
            + "      WHERE id = ?";
    private static String UPDATE = "UPDATE [dbo].[Product]\n"
            + "   SET [Name] = ?\n"
            + "      ,[Price] = ?\n"
            + "      ,[Stock] = ?\n"
            + "      ,[Image] = ?\n"
            + "      ,[Description] = ?\n"
            + " WHERE Id = ?";
    private static String TOP3 = "SELECT TOP 3 *FROM [LaptopShopManagement].[dbo].[Product]";
    private static String TOP4 = "SELECT TOP 4 *FROM [LaptopShopManagement].[dbo].[Product] order by id desc";
    private static String SEARCH = "SELECT *FROM [Product] where [name] like ?";

    public ArrayList<Product> searchProduct(String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = SEARCH;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, "%" + search + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),  String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category(rs.getInt("category_id"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select * from [Product] ";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category(rs.getInt("category_id"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getTop3Product() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = TOP3;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),  String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Product> getTop4Product() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = TOP4;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),  String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category()));
            }
        } catch (Exception e) {
             System.out.println(e);
        }
        return list;
    }

    public int getNumberProduct(String cid, String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "    select count(*) from Product p, Category c where p.category_id = c.category_id \n"
                + "                 and p.category_id like ?  and p.Name like ?";
        try {
            Connection conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Category> getCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "  select* from Category";
        try {
             Connection conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<Product> getProduct(String cid, String search, int index, String sort) {
        String sortby = "";
        switch (sort) {
            case "1":
                sortby = "order by p.create_date desc";
                break;
            case "2":
                sortby = "order by p.price asc";
                break;
            case "3":
                sortby = "order by p.price desc";
                break;
            default:
                sortby = "order by p.Name desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from [Product] p, Category c where p.category_id = c.category_id \n"
                + "and p.category_id like ?  and p.[Name] like ?  "
                + sortby
                + "  OFFSET ? ROWS FETCH NEXT 6  ROWS ONLY";
        try {
            Connection conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + cid + "%");
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, (index - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category(rs.getInt("category_id"))));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductById(String pid) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_BYID);
            ps.setString(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = (new Product(rs.getString(1), rs.getString(2), String.valueOf(rs.getInt(3)), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), new Category(rs.getInt("category_id"))));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void AddProduct(String name, String price, int stock, String img, String descri, String category) {
        String sql = INSERT;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setInt(3, stock);
            ps.setString(4, img);
            ps.setString(5, descri);
            ps.setString(6, category);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteProduct(String pid) {
        String sql = DELETE;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateProduct(int id, String name, String price, int stock, String descri, String img) {
        String sql = UPDATE;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setInt(3, stock);
            ps.setString(4, img);
            ps.setString(5, descri);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
//        ArrayList<Product> list = new ArrayList<>();
//        list = d.getTop4Product();
//        for (Product tea : list) {
//            System.out.println(tea.toString());
//        }
           System.out.println(d.getProductById("30"));
    }

}
