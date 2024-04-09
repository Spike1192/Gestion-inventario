/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author edgar
 */
public class LoginDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionSQL cn = new ConexionSQL();

    public login log(String usuario, String pass) {
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND pass=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setUsuario(rs.getString("usuario"));
                l.setPass(rs.getString("pass"));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }

}
