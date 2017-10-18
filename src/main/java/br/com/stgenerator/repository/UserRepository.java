package br.com.stgenerator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.stgenerator.models.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
