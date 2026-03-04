package nue.vocab.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nue.vocab.backend.model.Users;
import nue.vocab.backend.repository.UserRepository;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getAllUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users createUser(Users user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    // ✅ Fixed: accepts email and password separately
   public Users loginUser(String email, String password) {

    System.out.println("Login attempt started");
    System.out.println("Email received: " + email);
    System.out.println("Password received: " + password);

    Users user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                System.out.println("User not found in DB");
                return new IllegalArgumentException("User does not exist");
            });

    System.out.println("User found: " + user.getEmail());
    System.out.println("Password in DB: " + user.getPassword());

    if (!user.getPassword().equals(password)) {
        System.out.println("Password does NOT match");
        throw new IllegalArgumentException("Invalid password");
    }

    System.out.println("Password matched successfully");
    System.out.println("Login successful");

    return user;
}

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}