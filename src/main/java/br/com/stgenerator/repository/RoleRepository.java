package br.com.stgenerator.repository;

import br.com.stgenerator.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    public List<Role> findByRoleName(String roleName);

}
