package br.com.stgenerator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.stgenerator.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
	public List<Role> findByRoleName(String roleName);

}
