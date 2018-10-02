package com.edgar.escuela.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edgar.escuela.dao.AdminDAO;
import com.edgar.escuela.models.Administrador;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;

	@Override
	@Transactional
	public List<Administrador> getAll() {
		return adminDAO.getAll();
	}

	@Override
	@Transactional
	public Administrador getAdmin(Long id) {
		return adminDAO.getAdmin(id);
	}

	@Override
	@Transactional
	public Administrador getAdministradorByIdAndLevel(Long id, int level) {
		return adminDAO.getAdministradorByIdAndLevel(id, level);
	}

	@Override
	@Transactional
	public Administrador addAdmin(Administrador admin) {
		return adminDAO.addAdmin(admin);
	}

	@Override
	@Transactional
	public Administrador updateAdmin(Administrador admin) {
		return adminDAO.updateAdmin(admin);
	}

	@Override
	@Transactional
	public void deleteAdminLevelOne(Long id) {
		adminDAO.deleteAdminLevelOne(id);
	}
	
	
}
