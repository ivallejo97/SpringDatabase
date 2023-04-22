package com.example.SpringDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    public List<UserDTO> readAll(){
        List<User> users = userService.readAllUsers();
        /*List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users){
            userDTOS.add(new UserDTO(user));
        }*/
        return users.stream().map(user -> new UserDTO(user)).toList();
    }
    public UserDTO getUserById(Integer id) {
        User user = userService.getUserById(id);
        return new UserDTO(user);
    }
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return new UserDTO(userService.addUser(user));
    }
    public void deleteUser(Integer id) {
        userService.deleteUserById(id);
    }
    public void editUserById(UserDTO userDTO) {
        User user = new User(userDTO);
        userService.editUserById(user);
    }
    public void patchUser(Integer id, JsonPatch jsonPatch) {
        try {
            User user = userService.getUserById(id);
            User userPatched = userService.applyPatch(jsonPatch, user);
            userService.addUser(userPatched);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
