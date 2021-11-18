package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.UserRepository;
import com.mohamed.inventorymanagementsystem.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveNewUser(@Valid @RequestBody User user){
        String email= user.getEmail();
       Optional<User>userOptional= userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            new ResponseEntity<>(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }
}
