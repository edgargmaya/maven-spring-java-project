package com.edgar.escuela.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.edgar.escuela.models.Administrador;

public class AdminRowMapper implements RowMapper<Administrador> {

	@Override
	public Administrador mapRow(ResultSet resultSet, int arg1) throws SQLException {
		
		Administrador administrador = new Administrador();
		
		administrador.setId( resultSet.getInt("id") );
		administrador.setNombre( resultSet.getString("nombre") );
		administrador.setPrimerApellido( resultSet.getString("primer_apellido") );
		administrador.setSegundoApellido( resultSet.getString("segundo_apellido") );
		administrador.setNivel( resultSet.getInt("nivel") );
		administrador.setEmail( resultSet.getString("email") );
		
		return administrador;
	}

}
