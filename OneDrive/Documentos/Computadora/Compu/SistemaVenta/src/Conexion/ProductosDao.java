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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author edgar
 */
public class ProductosDao {
    Connection con;
    ConexionSQL cn = new ConexionSQL();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos (codigo,nombre,cantidad,precio) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getCantidad());
            ps.setDouble(4, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
    
   
    
  public List ListarProductos(){
      List<Productos> ListaPro = new ArrayList();
      String sql = "SELECT * FROM productos";
      try {
           con = cn.getConnection();
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
          while(rs.next()){
             Productos pro = new Productos();
             pro.setId(rs.getInt("id"));
             pro.setCodigo(rs.getString("codigo"));
             pro.setNombre(rs.getString("nombre"));
             pro.setCantidad(rs.getInt("cantidad"));
             pro.setPrecio(rs.getDouble("precio"));
             ListaPro.add(pro);
          }
      } catch (SQLException e) {
          System.out.println(e.toString());
      }
      return ListaPro;
  }
  public boolean EliminarProducto(int id){
      String sql = "DELETE FROM productos WHERE id = ?";
      try {
          ps=con.prepareStatement(sql);
          ps.setInt(1, id);
          ps.execute();
          return true;
      } catch (SQLException e) {
          System.out.println(e.toString());
          return false;
      }finally{
          try {
              con.close();
          } catch (SQLException e) {
              System.out.println(e.toString());
          }
      }
      
  }
  public boolean ModificarProducto(Productos pro){
      String sql = "UPDATE productos SET codigo=?, nombre=?, cantidad=?,precio=? WHERE id=?";
      try {
          ps = con.prepareStatement(sql);
          ps.setString(1, pro.getCodigo());
          ps.setString(2, pro.getNombre());
          ps.setInt(3, pro.getCantidad());
          ps.setDouble(4, pro.getPrecio());
          ps.setInt(5, pro.getId());
          ps.execute();
          return true;
      } catch (SQLException e) {
          System.out.println(e.toString());
          return false;
      }finally{
          try {
              con.close();
          } catch (SQLException e) {
              System.out.println(e.toString());
          }
      }
  }
  
  public Productos BuscarPro(String Cod){
      Productos producto = new Productos();
      
      String sql = "SELECT * FROM productos WHERE codigo = ?";
      try {
          con = cn.getConnection();
          ps = con.prepareStatement(sql);
          ps.setString(1, Cod);
          rs = ps.executeQuery();
          if(rs.next()){
              producto.setNombre(rs.getString("nombre"));
              producto.setPrecio(rs.getDouble("precio"));
              producto.setCantidad(rs.getInt("cantidad"));
          }
      } catch (SQLException e) {
          System.out.println(e.toString());
      }
      return producto;
  }
}
