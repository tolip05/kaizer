package springresrapi.kaizer.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springresrapi.kaizer.domein.models.service.UserServiceModel;

import java.util.Set;

public interface UserService extends UserDetailsService {
    boolean userCreate(UserServiceModel userServiceModel);
    Set<UserServiceModel> getAll();
}
