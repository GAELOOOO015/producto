package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Producto;
import Modelo.Usuario;
import crud.conexion;

public class daoProducto {
	conexion cx = null;

	public daoProducto() {
		cx = new conexion();
	}

	public boolean insertarProducto(Producto producto) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, producto.getDescripcion());
			ps.setString(2, convertirSHA256(producto.getPrecio()));
			ps.setString(3, producto.getCantidad());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Producto> fetchProductos() {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM producto");
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto u= new Producto();
				u.setId(rs.getInt("id"));
				u.setDescripcion(rs.getString("descripcion"));
				u.setPrecio(rs.getString("precio"));
				u.setCantidad(rs.getString("cantidad"));
				u.setProvedor(rs.getString("provedor"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public boolean loginProducto(Producto producto) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM producto WHERE user=? AND password=?");
			ps.setString(1, producto.getDescripcion());
			ps.setString(2, convertirSHA256(producto.getPrecio()));
			rs = ps.executeQuery();
	        if (rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean eliminarProducto(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM  producto WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarProducto(Producto producto) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE usuario SET user=?,password=?,nombre=? WHERE id=?");
			ps.setString(1, convertirSHA256(producto.getDescripcion()));
			ps.setString(2, producto.getPrecio());
			ps.setString(3, producto.getCantidad());
			ps.setString(4, producto.getProvedor());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();

		for (byte b : hash)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}
