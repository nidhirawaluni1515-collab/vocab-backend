package nue.vocab.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import nue.vocab.backend.service.UsersService;
import nue.vocab.backend.model.Users;
import nue.vocab.backend.routes.UserRoutes;
import java.util.List;

@RestController
public class UserController {
@Autowired
    private UsersService userService;
@GetMapping(UserRoutes.GET_ALL)
public ResponseEntity<List<Users>> getAllUsers(){
    return ResponseEntity.ok(userService.getAllUsers());}

@GetMapping(UserRoutes.GET_BY_ID)
public ResponseEntity<Users> getAllUserById(@PathVariable Long id){
    Users user = userService.getAllUserById(id);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(user);

}


@PostMapping(UserRoutes.CREATE)
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try {
            Users saved = userService.createUser(user);
            return ResponseEntity.ok(saved);
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

