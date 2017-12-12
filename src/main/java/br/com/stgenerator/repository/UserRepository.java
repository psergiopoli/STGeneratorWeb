package br.com.stgenerator.repository;

import br.com.stgenerator.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByName(String name);

    public User findByEmail(String name);

    @Query("SELECT u FROM user_all u")
    public List<User> getAllUsers();

}
