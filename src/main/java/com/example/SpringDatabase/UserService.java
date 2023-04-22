package com.example.SpringDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    public List<User> readAllUsers(){
        return userDAO.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> userOptional;
        userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        else return null;
    }
    public User addUser(User user){
        return userDAO.save(user);
    }
    public void deleteUserById(Integer id) {
        userDAO.deleteById(id);
    }
    public void editUserById(User user) {
        userDAO.save(user);
    }
    public User applyPatch(JsonPatch jsonPatch, User user) throws JsonProcessingException, JsonPatchException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = jsonPatch.apply(objectMapper.convertValue(user, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, User.class);
    }
}
