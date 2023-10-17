/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author 5.2023
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from ProductHE170280";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from CategoryHE170280";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from productHE170280 order by id desc";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from ProductHE170280 where cateID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from ProductHE170280 where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Product> searchProductByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from ProductHE170280 where [name] like ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public Account login(String user, String pass) {
        String query = "select * from AccountHE170280 where [user] = ? and pass = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "select * from AccountHE170280 where [user] = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void signup(String user, String pass) {
        String query = "insert into AccountHE170280 values(?,?,0,0)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<Product> getProductSellByID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from ProductHE170280 where sell_ID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public void deleteProduct(String pid) {
        String query = "delete from ProductHE170280 where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi voi sql sever
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, int category, int sid) {
        String query = "INSERT [dbo].[ProductHE170280] \n"
                + "([name], [image], [price], [title], [description], [cateID], [sell_ID])\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection(); // mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

    public void editProduct(String name, String image, String price,
            String title, String description, int category, String pid) {
        String query = "UPDATE [dbo].[ProductHE170280]\n"
                + "   SET [name] = ?,\n"
                + "      [image] = ?,\n"
                + "      price= ?,\n"
                + "      title = ?,\n"
                + "      [description] = ?,\n"
                + "      cateID = ?\n"
                + " WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); // mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setInt(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();

        // Insert a new product
        dao.insertProduct("New Product", "image.jpg", "19.99", "New Product Title", "Description", 11, 1);

        // Retrieve and display the list of products
        List<Product> list = dao.getAllProduct();
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
