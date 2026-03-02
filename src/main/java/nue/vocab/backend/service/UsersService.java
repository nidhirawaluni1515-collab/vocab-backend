package nue.vocab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nue.vocab.backend.model.Users;
import nue.vocab.backend.repository.UserRepository;
import java.util.List;


@Service
public class UsersService {
    @Autowired
    private  UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
    public Users getAllUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public Users createUser(Users user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("user is already exists");
        }
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
       if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
}}
