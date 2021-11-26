package com.mohamed.inventorymanagementsystem.resource;

import com.mohamed.inventorymanagementsystem.dao.UserRepository;
import com.mohamed.inventorymanagementsystem.dto.Staff;
import com.mohamed.inventorymanagementsystem.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Staff> saveNewUStaff(@Valid @RequestBody Staff staff){
        String email= staff.getEmail();
       Optional<Staff>userOptional= userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            new ResponseEntity<>(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(userRepository.save(staff),HttpStatus.CREATED);
    }
    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Staff> loadUsers()throws ResourceNotFound {
        List<Staff> staffList =userRepository.findAll();
        if (staffList.isEmpty()){
            throw new ResourceNotFound("List is Empty!");
        }
        return staffList;
    }
    @PutMapping(value = "/edit/{userId}/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public Staff updateUserById(@PathVariable("userId")Long userId, @Valid @RequestBody Staff newuser)throws ResourceNotFound{
        return userRepository.findById(userId).map(staff -> {
            staff.setFirstName(newuser.getFirstName());
            staff.setLastName(newuser.getLastName());
            staff.setAddress(newuser.getAddress());
            staff.setEmail(newuser.getEmail());
            staff.setPhone(newuser.getPhone());
            return userRepository.save(staff);
        }).orElseThrow(()->new ResourceNotFound("user with Id :"+userId+" not found"));
    }
    @DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable("id")Long id)throws ResourceNotFound{

        Optional<Staff>userOptional=userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new ResourceNotFound("user not found");
        }
         userRepository.deleteById(id);
    }
}
