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
import model.dao.DaoTipoEsporte;
import model.entities.TipoEsporte;

public class TipoEsporteDaoJDBC implements DaoTipoEsporte{

	private Connection conn;
	
	public TipoEsporteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(TipoEsporte obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tipo_esporte"
					+ "(nome_esporte)"
					+ "VALUES"
					+ "(?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome_esporte());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_esporte(id);
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
	public void deleteByNome_esporte(String nome_esporte) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tipo_esporte WHERE nome_esporte = ? ");
			st.setString(1, nome_esporte);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
		
	}
	
	@Override
	public List<TipoEsporte> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tipo_esporte");
			rs = st.executeQuery();
			
			List<TipoEsporte> list = new ArrayList<>();
			
			while(rs.next()) {
				TipoEsporte obj = new TipoEsporte();
				obj.setId_esporte(rs.getInt("id_esporte"));
				obj.setNome_esporte(rs.getString("nome_esporte"));
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
