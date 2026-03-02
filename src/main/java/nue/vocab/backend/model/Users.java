package nue.vocab.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "is_user_active", nullable = false)
    private boolean isUserActive;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername (String username){
        this.username = username;
    }
    public boolean isUserActive(){
        return isUserActive;
    }
    public void setUserActive(boolean userActive){
        isUserActive = userActive;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword (String password){
        this.password = password;
     }
    public LocalDateTime getCreatedAt(){
        return createdAt;   }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}