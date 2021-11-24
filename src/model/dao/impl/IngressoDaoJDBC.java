package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbProperties.DB;
import dbProperties.DbIntegrityException;
import model.dao.DaoIngresso;
import model.entities.Ingresso;

public class IngressoDaoJDBC implements DaoIngresso{

	private Connection conn;
	
	public IngressoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Ingresso obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO ingresso "
					+ "(id_partida, ingresso_disp, id_estadio) "
					+ "VALUES "
					+ "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId_partida());
			st.setInt(2, obj.getIngresso_disp());
			st.setInt(3, obj.getId_estadio());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_ingresso(id);
				}
			}
		}catch(SQLException e) {

		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
	}
	@Override
	public void deleteById(int id_partida) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM ingresso WHERE id_partida = ?");
			st.setInt(1, id_partida);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
	}
/*
	@Override
	public void update(Ingresso obj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Ingresso findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingresso findByPartida(Integer idPartida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingresso findByEstadio(String nomeEstadio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingresso> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
