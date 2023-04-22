package com.example.SpringDatabase;

import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {

    public static final String USERS = "/v1/users";
    @Autowired
    UserController userController;

    @GetMapping
    public List<UserDTO> users(){
        return userController.readAll();
    }

    @GetMapping("/{id}")
    public UserDTO user(@PathVariable Integer id){
        return userController.getUserById(id);
    }

    @GetMapping("/{id}/email")
    public Map<String,String> userEmail(@PathVariable Integer id){
        return Collections.singletonMap("email",userController.getUserById(id).getEmail());
    }
    @PostMapping
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userController.addUser(userDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userController.deleteUser(id);
    }
    @PutMapping("{id}")
    public void replace(@RequestBody UserDTO userDTO, @PathVariable Integer id){
        UserDTO userDTO1 = userController.getUserById(id);
        userDTO1.setId(userDTO.getId());
        userDTO1.setFullName(userDTO.getFullName());
        userDTO1.setEmail(userDTO.getEmail());
        userDTO1.setPassword(userDTO.getEmail());
        userController.editUserById(userDTO);
    }
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void updateUser(@PathVariable Integer id, @RequestBody JsonPatch jsonPatch) {
        userController.patchUser(id, jsonPatch);
    }


}


