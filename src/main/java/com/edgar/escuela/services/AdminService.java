package com.edgar.escuela.services;

import java.util.List;

import com.edgar.escuela.models.Administrador;

public interface AdminService {
	
	public abstract List<Administrador> getAll();
	
	public abstract Administrador getAdmin( Long id );
	
	public abstract Administrador getAdministradorByIdAndLevel( Long id, int level );
	
	public abstract Administrador addAdmin( Administrador admin );
	
	public abstract Administrador updateAdmin( Administrador admin );
		
	public abstract void deleteAdminLevelOne( Long id );
	
}
