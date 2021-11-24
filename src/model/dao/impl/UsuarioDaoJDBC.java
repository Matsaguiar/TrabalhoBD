package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbProperties.DB;
import dbProperties.DbException;
import dbProperties.DbIntegrityException;
import model.dao.DaoUsuario;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements DaoUsuario{

	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO usuario"
					+ "(cpf, senha, nome, idade)"
					+ "VALUES"
					+ "(?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getCpf());
			st.setString(2, obj.getSenha());
			st.setString(3, obj.getNome());
			st.setInt(4, obj.getIdade());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_usuario(id);
				}
			}
			
			if(rowsAffected==0) {
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
	public void update(Usuario obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(//o usuario só podera ter sua senha alterada
					"UPDATE usuario "
					+"SET senha = ? "
					+"WHERE cpf = ?");
			st.setString(1, obj.getSenha());
			st.setString(2, obj.getCpf());
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
		
	}

	@Override
	public void deleteBycpf(String cpf) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM usuario WHERE cpf = ?");
			st.setString(1, cpf);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
		
	}

	@Override
	public Usuario findByCpf(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usuario WHERE cpf = ?");
			st.setString(1, cpf);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario obj = new Usuario();
				obj.setCpf(rs.getString("cpf"));
				obj.setNome(rs.getString("nome"));
				obj.setIdade(rs.getInt("idade"));
				obj.setSenha(rs.getString("senha"));
				obj.setId_usuario(rs.getInt("id_usuario"));
				return obj;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}

	@Override
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usuario ORDER BY nome");
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			
			while(rs.next()) {
				Usuario obj = new Usuario();
				obj.setCpf(rs.getString("cpf"));
				obj.setNome(rs.getString("nome"));
				obj.setIdade(rs.getInt("idade"));
				obj.setSenha(rs.getString("senha"));
				obj.setId_usuario(rs.getInt("id_usuario"));
				list.add(obj);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}

}
