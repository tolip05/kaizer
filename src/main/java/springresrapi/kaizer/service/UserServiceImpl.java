package springresrapi.kaizer.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springresrapi.kaizer.domein.entities.User;
import springresrapi.kaizer.domein.entities.UserRole;
import springresrapi.kaizer.domein.models.service.UserServiceModel;
import springresrapi.kaizer.repositories.UserRepository;
import springresrapi.kaizer.repositories.UserRoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private Set<UserRole> getAuthorities(String authority){
        Set<UserRole> userAuthorities = new HashSet<>();
        userAuthorities.add(this.userRoleRepository.getByAuthority(authority));

        return userAuthorities;
    }
    @Override
    public boolean userCreate(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel,User.class);
        userEntity.setPassword(this.bCryptPasswordEncoder
                .encode(userEntity.getPassword()));
        if (this.userRepository.findAll().isEmpty()){
            userEntity.setAuthorities(this.getAuthorities("ADMIN"));
        }else {
            userEntity.setAuthorities(this.getAuthorities("USER"));
        }
        return this.userRepository.save(userEntity) != null;
    }

    @Override
    public Set<UserServiceModel> getAll() {
        return this.userRepository.findAll()
                .stream().map(x-> this.modelMapper
                .map(x,UserServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
       User user = this.userRepository.findUsersByUsername(username)
               .orElse(null);
       if(user == null)throw new UsernameNotFoundException("No Such user");

        return user;
    }
}
