/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.utils.DBUtils;

/**
 *
 * @author chi
 */
public class CategoryDAO {

    public void AddCategory(String name) {
        String sql = "INSERT INTO [dbo].[Category]\n"
                + "           ([category_name])\n"
                + "     VALUES\n"
                + "           (?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateCategory(int id, String name) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [category_name] = ?\n"
                + " WHERE [category_id] = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void DeleteCategory(String pid) {
        String sql = "delete from Category where [category_id] = ?";
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
    public Category getCategoryById(String pid) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            PreparedStatement ps = conn.prepareStatement("Select * from Category where [category_id] =?");
            ps.setString(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category p = (new Category(rs.getInt(1), rs.getString(2)));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
