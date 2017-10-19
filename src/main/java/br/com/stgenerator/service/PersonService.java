package br.com.stgenerator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.stgenerator.models.Person;
import br.com.stgenerator.repository.PersonRepository;

@Service
public class PersonService implements UserDetailsService{

	PersonRepository pr;
	
	@Autowired
	public PersonService(PersonRepository pr) {
		this.pr = pr;
	}
	
	public Person createUser(Person person){
		
		return null;
	}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = pr.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);

        return userDetails;
    }
}
