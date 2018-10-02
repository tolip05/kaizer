package springresrapi.kaizer.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springresrapi.kaizer.domein.models.binding.UserRegisterBindingModel;
import springresrapi.kaizer.domein.models.service.UserServiceModel;
import springresrapi.kaizer.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity register(UserRegisterBindingModel
                                    userRegisterBindingModel) throws URISyntaxException {
       if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel
       .getConfirmPassword())){
           return ResponseEntity.badRequest()
                   .body("Error: Password is not match");
       }

        boolean result = this.userService.userCreate(this.modelMapper
        .map(userRegisterBindingModel, UserServiceModel.class));
    return ResponseEntity.created(new URI("/users/register")).body(result);
    }
}
