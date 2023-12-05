/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author admin
 */
public class UserDAO {

    private static String LOGIN = "SELECT * FROM Users WHERE username= ? AND password= ?";
    private static String SEARCH = "SELECT userID, fullName, roleID FROM Users WHERE fullName like  ?";
    private static String DELETE = "DELETE Users WHERE id =?";
    private static String UPDATE = "UPDATE Users SET fullName = ?, roleID=? WHERE id=?";
    private static String CHECK_DUPLICATE = "SELECT fullName FROM Users WHERE id = ?";
    private static String INSERT = "INSERT INTO [dbo].[Users]\n"
            + "           ([Username]\n"
            + "           ,[Fullname]\n"
            + "           ,[Password]\n"
            + "           ,[Address]\n"
            + "           ,[Phone]\n"
            + "           ,[RoleId])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?)";
    private static String GETBY_USERNAME = "Select *from Users where username = ?";
      public boolean insert(User user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUsername());
                ptm.setString(2, user.getFullName());
                ptm.setString(6, "US");
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getPhone());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    user = new UserDTO(userID, fullName, roleID, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public static void main(String[] args) throws SQLException {
        UserDAO d = new UserDAO();
        //   UserDTO u = d.checkLogin("abc", "123");
        // System.out.println(u.toString());
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "^!^";
                    list.add(new UserDTO(userID, fullName, roleID, password));

                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return list;
    }

    public boolean delete(String userID) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                checkDelete = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return checkDelete;
    }

    public boolean update(UserDTO user) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getUserID());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkUpdate;
    }

    public boolean checkduplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

  

    public User getUserByUserName(String username) throws SQLException {
        User user = new User();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBY_USERNAME);
                ptm.setString(1, username);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString("userID");
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    user = new User(userId, "", fullName, "", "","",roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
}
