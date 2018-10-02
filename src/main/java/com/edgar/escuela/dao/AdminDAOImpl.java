package com.edgar.escuela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.edgar.escuela.exceptions.UserExistException;
import com.edgar.escuela.exceptions.UserNotFoundException;
import com.edgar.escuela.mappers.AdminRowMapper;
import com.edgar.escuela.models.Administrador;
import com.mysql.jdbc.Statement;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Administrador> getAll() {
		
		List<Administrador> adminList = new ArrayList<>();
		
		Collection< Map<String, Object> > rows = jdbcTemplate.queryForList("SELECT * FROM Administradores");
		
		rows.parallelStream().map( element -> {
			Administrador administrador = new Administrador();
			
			administrador.setId( (int) element.get("id") );
			administrador.setNombre( (String) element.get("nombre") );
			administrador.setPrimerApellido( (String) element.get("primer_apellido") );
			administrador.setSegundoApellido( (String) element.get("segundo_apellido") );
			administrador.setNivel( (int) element.get("nivel") );
			administrador.setEmail( (String) element.get("email") );
			
			return administrador;
		}).forEach( item -> {
			adminList.add(item);
		});
		
		/*
		 * This could be a valid implementation to retrieve all the values, using iterables.
		 * Esta podria ser una implementación valida para recuperar todos los valores, utilizando iterables.
		 * 
		Iterator< Map<String, Object> > listIterator = rows.iterator();
		
		while( listIterator.hasNext() ) {
			
			LinkedCaseInsensitiveMap<Object> element = (LinkedCaseInsensitiveMap<Object>) listIterator.next();
			
			Administrador administrador = new Administrador();
			
			administrador.setNivel( (int) element.get("Nivel") );
			administrador.setClave( (String) element.get("Clave") );
			administrador.setIdAdmin( (String) element.get("idAdmin") );
			administrador.setNombre( (String) element.get("Nombre") );
			administrador.setApellidoM( (String) element.get("ApellidoM") );
			administrador.setApellidoP( (String) element.get("ApellidoP") );
			
			adminList.add( administrador );
		}
		*/
		
		return adminList;
	}
	
	
	@Override
	public Administrador getAdmin(Long clave) throws DataAccessException {
		
		String query = "SELECT * FROM Administradores WHERE id = ?";
		
		Administrador administrador;
		
		try {
			administrador = jdbcTemplate.queryForObject( query, new Object[]{ clave }, new AdminRowMapper() );
		} catch( EmptyResultDataAccessException e ) {
			throw new UserNotFoundException("No se encontró el Administrador especificado");
		} catch( DataAccessException e ) {
			e.printStackTrace();
			return null;
		}
		
		return administrador;
		
	}
	
	@Override
	public Administrador getAdministradorByIdAndLevel(Long id, int level) throws DataAccessException {
		
		String query = "SELECT * FROM Administradores WHERE id = ? and nivel = ?";
		Administrador administrador;
		
		try {
			administrador = jdbcTemplate.queryForObject( query, new Object[]{ id, level }, new AdminRowMapper() );
		} catch( EmptyResultDataAccessException e ) {
			throw new UserNotFoundException("No se encontró el Administrador especificado");
		}
		
		return administrador;
	}
	
	
	
	//==========================================================
	//					Añadir Administrador
	//==========================================================
	
	@Override
	public Administrador addAdmin(Administrador admin) {
		
		KeyHolder generatedId = new GeneratedKeyHolder();
		
		try {
			jdbcTemplate.update( (Connection connection) -> {
				
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("INSERT INTO ADMINISTRADORES ( nombre, primer_apellido, segundo_apellido, nivel, email, password ) VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS );
				
				preparedStatement.setString(1, admin.getNombre() );
				preparedStatement.setString(2, admin.getPrimerApellido() );
				preparedStatement.setString(3, admin.getSegundoApellido() );
				preparedStatement.setInt(4, admin.getNivel() );
				preparedStatement.setString(5, admin.getEmail() );
				preparedStatement.setString(6, admin.getPassword() );
				
				return preparedStatement;
			}, generatedId );
			
		} catch( DuplicateKeyException e ) {
			e.printStackTrace();
			throw new UserExistException("Error al añadir nuevo administrador, El correo ya se encuentra asociado a otra cuenta.");
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		return getAdmin( generatedId.getKey().longValue() );
	}
		

	@Override
	public Administrador updateAdmin(Administrador admin) {
		
		String query = "UPDATE Administradores SET nombre = ?, primer_apellido = ?, segundo_apellido = ?, nivel = ?, email = ? WHERE id = ?";
		
		try {
			
			int op = jdbcTemplate.update( (Connection con) -> {
				PreparedStatement preparedStatement = con.prepareStatement(query);
				
				preparedStatement.setString(1, admin.getNombre() );
				preparedStatement.setString(2, admin.getPrimerApellido()  );
				preparedStatement.setString(3, admin.getSegundoApellido() );
				preparedStatement.setInt(4, admin.getNivel() );
				preparedStatement.setString(5, admin.getEmail() );
				preparedStatement.setInt(6, admin.getId() );
				
				return preparedStatement;
			});
			
			if(op < 1)
				throw new EmptyResultDataAccessException(0);	
			
		} catch( EmptyResultDataAccessException e ) {
			throw new UserNotFoundException("No se encontró el Administrador especificado");
		}
		
		return this.getAdmin( (long) admin.getId() );
	}

	@Override
	public void deleteAdminLevelOne(Long id) throws DataAccessException {
		String query = "DELETE FROM Administradores WHERE nivel = 1 and id = ?";
		try {
			if( jdbcTemplate.update(query, new Object[] { id }) < 1 )
				throw new EmptyResultDataAccessException(0);
		} catch( EmptyResultDataAccessException e ) {
			throw new UserNotFoundException("No se encontró el Administrador especificado");
		}
	}
	
}
