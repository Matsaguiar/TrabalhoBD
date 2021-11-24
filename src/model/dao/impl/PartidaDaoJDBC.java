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
import model.dao.DaoPartida;
import model.entities.Partida;

public class PartidaDaoJDBC implements DaoPartida{

	private Connection conn;
	
	public PartidaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Partida obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO partida "
					+ "(id_partida, nome_partida, id_esporte, data_partida, horario_partida, id_estadio, valor_ingresso) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId_partida());
			st.setString(2, obj.getNome_partida());
			st.setInt(3, obj.getId_esporte());
			st.setString(4, obj.getData_partida());
			st.setString(5, obj.getHorario_partida());
			st.setInt(6, obj.getId_estadio());
			st.setInt(7, obj.getValor_ingresso());
			
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
	public void update(Partida obj) { //so pode alterar a data do jogo
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE partida "
					+ "SET data_partida = ? "
					+ "WHERE id_partida = ?");
			
			st.setString(1, obj.getData_partida());
			st.setInt(2, obj.getId_partida());
			
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
			st = conn.prepareStatement("DELETE FROM partida WHERE id_partida = ?");
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
	public List<Partida> findByEstadio(String nome_estadio) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id_partida, nome_partida, data_partida, valor_ingresso from partida, estadio where partida.id_estadio = estadio.id_estadio and estadio.nome_estadio = ?");
			st.setString(1, nome_estadio);
			rs = st.executeQuery();
			
			List<Partida> list = new ArrayList<>();
			
			while(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setValor_ingresso(rs.getInt("valor_ingresso"));
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
	public Partida findById_partida(Integer id_partida) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM partida WHERE id_partida = ?");
			st.setInt(1, id_partida);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setId_esporte(rs.getInt("id_esporte"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setHora_partida(rs.getString("horario_partida"));
				obj.setId_estadio(rs.getInt("id_estadio"));
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
	public List<Partida> findByEstado(String estado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso from partida, estadio, tipo_esporte where partida.id_esporte = tipo_esporte.id_esporte and partida.id_estadio = estadio.id_estadio and estadio.estado = ?");
			st.setString(1, estado);
			rs = st.executeQuery();
			
			List<Partida> list = new ArrayList<>();
			
			while(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setValor_ingresso(rs.getInt("valor_ingresso"));
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
	public List<Partida> findByEsporte(String nome_esporte) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso from partida, estadio, tipo_esporte where partida.id_esporte = tipo_esporte.id_esporte and partida.id_estadio = estadio.id_estadio and tipo_esporte.nome_esporte = ?");
			st.setString(1, nome_esporte);
			rs = st.executeQuery();
			
			List<Partida> list = new ArrayList<>();
			
			while(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setValor_ingresso(rs.getInt("valor_ingresso"));
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
	public List<Partida> findAll(int id_usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT distinct partida.id_partida, partida.nome_partida, partida.data_partida, estadio.nome_estadio, partida.valor_ingresso FROM gerente, partida, estadio, usuario, ingresso where gerente.id_partida = partida.id_partida and partida.id_estadio = estadio.id_estadio and gerente.id_usuario = ?");
			st.setInt(1, id_usuario);
			rs = st.executeQuery();
			
			List<Partida> list = new ArrayList<>();
			
			while(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setValor_ingresso(rs.getInt("valor_ingresso"));
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
	public List<Partida> todasPartidas() {		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso from partida, estadio where partida.id_estadio = estadio.id_estadio");
			//st.setInt(1, );
			rs = st.executeQuery();
			
			List<Partida> list = new ArrayList<>();
			
			while(rs.next()) {
				Partida obj = new Partida();
				obj.setId_partida(rs.getInt("id_partida"));
				obj.setNome_partida(rs.getString("nome_partida"));
				obj.setData_partida(rs.getString("data_partida"));
				obj.setNome_estadio(rs.getString("nome_estadio"));
				obj.setValor_ingresso(rs.getInt("valor_ingresso"));
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
