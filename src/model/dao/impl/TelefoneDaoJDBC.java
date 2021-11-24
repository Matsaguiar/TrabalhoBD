package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbProperties.DB;
import dbProperties.DbException;
import dbProperties.DbIntegrityException;
import model.dao.DaoTelefone;
import model.entities.Telefone;

public class TelefoneDaoJDBC implements DaoTelefone{

	private Connection conn;
	
	public TelefoneDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Telefone obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO telefone"
					+ "(id_usuario, fone)"
					+ "VALUES"
					+ "(?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId_usuario());
			st.setString(2, obj.getFone());
			
			int rowsAffected = st.executeUpdate();
			
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
	public void deleteByFone(String fone) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM telefone WHERE fone = ?");
			st.setString(1, fone);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
	}

	@Override
	public void update(String antigo, String novo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"UPDATE telefone "
							+"SET fone = ? "
							+"WHERE fone = ?");
				st.setString(1, novo);
				st.setString(2, antigo);
		
				st.executeUpdate();		
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}
	
	@Override
	public void findAll(Integer id_usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM telefone where id_usuario = ?");
				st.setInt(1, id_usuario);
			
				rs = st.executeQuery();
			
			//List<Telefone> list = new ArrayList<>();
			int i = 1;
			while(rs.next()) {
				Telefone obj = new Telefone();
				obj.setId_usuario(rs.getInt("id_usuario"));
				obj.setFone(rs.getString("fone"));
				System.out.println("Telefone "+ i + ": " + obj.getFone());
				i++;
				//list.add(obj);
			}
			//return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			//DB.closeConnection();
		}
	}

}
