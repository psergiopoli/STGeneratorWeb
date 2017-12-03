package br.com.stgenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.stgenerator.models.Role;
import br.com.stgenerator.models.User;
import br.com.stgenerator.repository.RoleRepository;
import br.com.stgenerator.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository,RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
    	User userTest = userRepository.findByEmail("psergiopoli@gmail.com");    	
    	if(userTest==null){
	    	List<Role> roles = new ArrayList<Role>();
	    	
	    	Role role1 = new Role();
	    	role1.setDescription("Common user");
	    	role1.setRoleName("ROLE_USER");
	    	roleRepository.save(role1);
	    	
	    	Role role2 = new Role();
	    	role2.setDescription("Admin user");
	    	role2.setRoleName("ROLE_ADMIN");
	    	roleRepository.save(role2);
	    	
	    	roles.add(role1);
	    	roles.add(role2);
	    	
	        User user = new User();
	        user.setPassword(bCryptPasswordEncoder.encode("h4ck3rlnx"));
	        user.setEmail("psergiopoli@gmail.com");
	        user.setRoles(roles);
	        user.setName("Paulo Sergio Jr");
	        user.setFavoriteMeme("Vin Diesel BR");
	        
	        userRepository.save(user);
    	}
    }
}