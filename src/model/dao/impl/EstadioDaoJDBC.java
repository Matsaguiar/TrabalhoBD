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
import model.dao.DaoEstadio;
import model.entities.Estadio;

public class EstadioDaoJDBC implements DaoEstadio{
	
	private Connection conn;
	
	public EstadioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Estadio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO estadio "
					+ "(nome_estadio, quant_ingresso, estado, cidade) "
					+ "VALUES "
					+ "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome_estadio());
			st.setInt(2, obj.getQuant_ingresso());
			st.setString(3, obj.getEstado());
			st.setString(4, obj.getCidade());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_estadio(id);
				}
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Estadio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE estadio "
					+ "SET quant_ingresso = ? "
					+ "WHERE nome_estadio = ?");
			
			st.setInt(1, obj.getQuant_ingresso());
			st.setString(2, obj.getNome_estadio());
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteByNome(String nome) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM estadio WHERE nome_estadio = ?");
			st.setString(1, nome);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Estadio findByNome(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM estadio WHERE nome_estadio = ?");
			st.setString(1, nome);
			rs = st.executeQuery();
			if(rs.next()) {
				Estadio obj = new Estadio();
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setQuant_ingresso(rs.getInt("quant_ingresso"));
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
	public Estadio findbtEstado(String estado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM estadio WHERE estado = ?");
			st.setString(1, estado);
			rs = st.executeQuery();
			if(rs.next()) {
				Estadio obj = new Estadio();
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setQuant_ingresso(rs.getInt("quant_ingresso"));
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
	public List<Estadio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM estadio ORDER BY nome_estadio");
			rs = st.executeQuery();
			
			List<Estadio> list = new ArrayList<>();
			
			while(rs.next()) {
				Estadio obj = new Estadio();
				obj.setCidade(rs.getString("cidade"));
				obj.setEstado(rs.getString("estado"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setQuant_ingresso(rs.getInt("quant_ingresso"));
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
