package br.com.stgenerator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.stgenerator.models.Person;

@Transactional
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
	
	public Person findByName(String name);

}
