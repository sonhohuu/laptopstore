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
import sample.user.User;
import sample.utils.DBUtils;

/**
 *
 * @author chi
 */
public class OrderDAO {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private static String INSERT_ORDER = "INSERT INTO [dbo].[Order]\n"
            + "           ([UserId]\n"
            + "           ,[Order_date]\n"
            + "           ,[Total]\n"
            + "           ,[Note])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,GETDATE()\n"
            + "           ,?\n"
            + "           ,?)";

    private static String INSERT_ORDER_DETAIL = "INSERT INTO [dbo].[OrderDetail]\n"
            + "           ([OrderId]\n"
            + "           ,[ProductId]\n"
            + "           ,[Price]\n"
            + "           ,[Quantity])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?)";
    private static String GETBY_USERID = "select * from [Order] where userid = ?";
    private static String GETODETAILBY_OID = "select * from [OrderDetail] where orderid = ?";

    public OrderDAO() throws ClassNotFoundException, SQLException {
        this.conn = DBUtils.getConnection1();
    }

    public void insertOrder(User u, Cart cart) throws ClassNotFoundException, SQLException {
        Connection conn = DBUtils.getConnection1();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = INSERT_ORDER;
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUserId());
            ps.setDouble(2, cart.getTotalMoney());
            ps.setString(3, cart.getNotes());
            ps.executeUpdate();
            String sql1 = "select top 1 [Id] from [Order] order by [Id] desc";
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (CartItem item : cart.getItems()) {
                    String sql2 = INSERT_ORDER_DETAIL;
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, oid);
                    ps.setString(2, item.getProduct().getId());
                    ps.setString(3, item.getProduct().getPrice());
                    ps.setInt(4, item.getQuantity());
                    ps.executeUpdate();
                }
            }
            String sql3 = "update [dbo].[Product] set [Stock] = [Stock] - ? "
                    + "where id = ?";
            ps = conn.prepareStatement(sql3);
            for (CartItem item : cart.getItems()) {
                ps.setInt(1, item.getQuantity());
                ps.setString(2, item.getProduct().getId());
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }
    }

    public ArrayList<Order> getAllOrderByuId(String uid) throws ClassNotFoundException, SQLException {
        ArrayList<Order> ol = new ArrayList<>();
        String sql = GETBY_USERID;
        conn = DBUtils.getConnection1();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ol.add(new Order(rs.getInt(1), new User(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return ol;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        OrderDAO d = new OrderDAO();
        ArrayList<OrderDetail> ol = new ArrayList<>();
        ol = d.getAllOrderDetailByoId("17");
        System.out.println(ol.toString());
    }
    ProductDAO dao = new ProductDAO();

    public ArrayList<OrderDetail> getAllOrderDetailByoId(String oid) throws ClassNotFoundException, SQLException {
        ArrayList<OrderDetail> odl = new ArrayList<>();
        String sql = GETODETAILBY_OID;
        conn = DBUtils.getConnection1();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(oid));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odl.add(new OrderDetail(rs.getInt(1), rs.getInt(2), dao.getProductById(rs.getString(3)), rs.getString(4), rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return odl;
    }

}
