package nue.vocab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nue.vocab.backend.service.UsersService;
import nue.vocab.backend.auth.JwtUtil;
import nue.vocab.backend.model.Users;
import nue.vocab.backend.dto.LoginRequest;
import nue.vocab.backend.dto.AuthResponse;
import nue.vocab.backend.routes.UserRoutes;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UsersService userService;

    @Autowired // ✅ Fix: Use @Autowired instead of manual instantiation
    private JwtUtil jwtUtil;

    @GetMapping(UserRoutes.GET_ALL)
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(UserRoutes.GET_BY_ID)
    public ResponseEntity<Users> getAllUserById(@PathVariable Long id) {
        Users user = userService.getAllUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(UserRoutes.CREATE)
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try {
            System.out.println("Received user data: " + user);
            Users saved = userService.createUser(user);
            String token = jwtUtil.generateToken(saved.getEmail());
            return ResponseEntity.ok(
                Map.of(
                    "token", token,
                    "user", saved
                )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(UserRoutes.LOGIN)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            Users user = userService.loginUser(request.getEmail(), request.getPassword());
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, user.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(UserRoutes.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}