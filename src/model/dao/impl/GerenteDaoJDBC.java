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
import model.dao.DaoGerente;
import model.entities.Gerente;

public class GerenteDaoJDBC implements DaoGerente{

	private Connection conn;
	
	public GerenteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Gerente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO gerente "
					+ "(id_usuario, id_partida) "
					+ "VALUES "
					+ "(?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId_usuario());
			st.setInt(2, obj.getId_partida());
			
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
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM gerente WHERE id_partida = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			//DB.closeConnection();
		}
	}
	
	@Override
	public Gerente findById_partida(int id_partida) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM gerente WHERE id_partida = ?");
			st.setInt(1, id_partida);
			rs = st.executeQuery();
			if(rs.next()) {
				Gerente obj = new Gerente();
				obj.setId_usuario(rs.getInt("id_usuario"));
				obj.setId_partida(rs.getInt("id_partida"));
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
	public List<Gerente> findById_usuario(int id_usuario){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM gerente");
			rs = st.executeQuery();
			
			List<Gerente> list = new ArrayList<>();
			
			while(rs.next()) {
				Gerente obj = new Gerente();
				obj.setId_usuario(rs.getInt("id_usuario"));
				obj.setId_partida(rs.getInt("id_partida"));
	
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

	@Override
	public List<Gerente> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM gerente");
			rs = st.executeQuery();
			
			List<Gerente> list = new ArrayList<>();
			
			while(rs.next()) {
				Gerente obj = new Gerente();
				obj.setId_usuario(rs.getInt("id_usuario"));
				obj.setId_partida(rs.getInt("id_partida"));
	
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
