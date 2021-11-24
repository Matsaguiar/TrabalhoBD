package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dbProperties.DB;
import dbProperties.DbException;
import model.dao.DaoCartao;
import model.entities.Cartao;

public class CartaoDaoJDBC implements DaoCartao{

	private Connection conn;
	
	public CartaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Cartao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO cartao_credito"
					+ "(id_usuario, numero, validade, cod_seguranca)"
					+ "VALUES"
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId_usuario());
			st.setString(2, obj.getNumero());
			st.setString(3, obj.getValidade());
			st.setString(4, obj.getCod_segurança());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_cartao(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
		
	}

	@Override
	public void update(Cartao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE cartao_credito"
					+ "SET id_cartao = ?, numero = ?, validade = ?, cod_seguranca = ?"
					+ "WHERE id_usuario = ?");
			
			st.setInt(1, obj.getId_cartao());
			st.setString(2, obj.getNumero());
			st.setString(3, obj.getValidade());
			st.setString(4, obj.getCod_segurança());
			st.setInt(5, obj.getId_usuario());
			
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM cartao_credito WHERE id_cartao = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
	}

	/*private Cartao instantiateCartao(ResultSet rs, Usuario us) throws SQLException{
		Cartao obj = new Cartao();
		obj.setId_cartao(rs.getInt("id_cartao"));
		obj.setNumero(rs.getString("numero"));
		obj.setValidade(rs.getString("validade"));
		obj.setCod_seguranca(rs.getString("cod_seguranca"));
		obj.setUsuario(us);
		return obj;
	}
	
	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		
		Usuario usuario = new Usuario();
		usuario.setId_usuario(rs.getInt("id_usuario"));
		usuario.setIdade(rs.getInt("idade"));
		usuario.setCpf(rs.getString("cpf"));
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
		
		usuario.setNome(rs.getString("nome"));
		usuario.setSenha(rs.getString("senha"));
		return usuario;
	}*/
	
	
	@Override
	public List<Cartao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cartao_credito.*, usuario.id_usuario "
					+ "FROM cartao_credito INNER JOIN usuario "
					+ "ON cartao_credito.id_usuario = usuario.id_usuario "
					+ "ORDER BY validade");
			rs = st.executeQuery();
			
			List<Cartao> list = new ArrayList<>();
			/*Map<Integer, Usuario> map = new HashMap<>();
			
			/while(rs.next()) {
				Usuario usu = map.get(rs.getInt("cartao_credito.id_usuario"));
				
				if(usu==null) {
					usu = instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), usu);
				}
				
				Cartao obj = instantiateCartao(rs, usu);
				list.add(obj);
			}*/
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}

	@Override
	public List<Cartao> findByIdUsuario(int id_usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT cartao_credito.*,usuario.id_usuario as Dono "
					+ "FROM cartao_credito INNER JOIN usuario "
					+ "ON cartao_credito.id_usuario = usuario.id_usuario "
					+ "WHERE cartao_credito.id_usuario = ? "
					+ "ORDER BY id_cartao");
			st.setInt(1, id_usuario);
			rs = st.executeQuery();	
			
			List<Cartao> list = new ArrayList<>();
			while(rs.next()) {
				Cartao obj = new Cartao();
				obj.setId_cartao(rs.getInt("id_cartao"));
				obj.setNumero(rs.getString("numero"));
				obj.setValidade(rs.getString("validade"));
				obj.setCod_seguranca(rs.getString("cod_seguranca"));
				list.add(obj);
			}
			return list;
			
			/*Map<Integer, Usuario> map = new HashMap<>();
			
			while(rs.next()) {
				Usuario usu = map.get(rs.getInt("id_usuario"));
				
				if(usu==null) {
					usu = instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), usu);
				}
				
				Cartao obj = instantiateCartao(rs, usu);
				list.add(obj);
			}*/
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}

}
