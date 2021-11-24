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
import model.dao.DaoJogosDisponiveis;
import model.entities.JogosDisponiveis;

public class JogosDisponiveisDaoJDBC implements DaoJogosDisponiveis{

	private Connection conn;
	
	
	public JogosDisponiveisDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insert(JogosDisponiveis obj) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement(
					"INSERT INTO jogos_disponiveis "
					+ "(id_partida, nome_partida, qtd_ingressos) "
					+ "VALUES "
					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId_partida());
			st.setString(2, obj.getNome_partida());
			st.setInt(3, obj.getQtd_ingressos());
			
			if(st.executeUpdate()<=0) {
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
	public void update(JogosDisponiveis obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE jogos_disponiveis "
					+ "SET nome_partida = ?, qtd_ingressos = ? "
					+ "WHERE id_partida = ?");
			st.setString(1, obj.getNome_partida());
			st.setInt(2, obj.getQtd_ingressos());
			
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
			st = conn.prepareStatement("DELETE FROM jogos_disponiveis WHERE id_partida = ?");
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
	public JogosDisponiveis findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM jogos_disponiveis WHERE id_partida = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				JogosDisponiveis obj = new JogosDisponiveis();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setQtd_ingressos(rs.getInt("qtd_ingressos"));
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
	public List<JogosDisponiveis> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM jogos_disponiveis ORDER BY id_partida");
			rs = st.executeQuery();
			
			List<JogosDisponiveis> list = new ArrayList<>();
			
			while(rs.next()) {
				JogosDisponiveis obj = new JogosDisponiveis();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setQtd_ingressos(rs.getInt("qtd_ingressos"));
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
