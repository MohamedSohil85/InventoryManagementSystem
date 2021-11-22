package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.UserRepository;
import com.mohamed.inventorymanagementsystem.dto.User;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
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
    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> loadUsers()throws ResourceNotFound {
        List<User>userList=userRepository.findAll();
        if (userList.isEmpty()){
            throw new ResourceNotFound("List is Empty!");
        }
        return userList;
    }
    @PutMapping(value = "/edit/{userId}/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUserById(@PathVariable("userId")Long userId,@Valid @RequestBody User Newuser)throws ResourceNotFound{
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(Newuser.getFirstName());
            user.setLastName(Newuser.getLastName());
            user.setAddress(Newuser.getAddress());
            user.setEmail(Newuser.getEmail());
            user.setPhone(Newuser.getPhone());
            return userRepository.save(user);
        }).orElseThrow(()->new ResourceNotFound("user with Id :"+userId+" not found"));
    }
    @DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable("id")Long id)throws ResourceNotFound{

        Optional<User>userOptional=userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new ResourceNotFound("user not found");
        }
         userRepository.deleteById(id);
    }
}
