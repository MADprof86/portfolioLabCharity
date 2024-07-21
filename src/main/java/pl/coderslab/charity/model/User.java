package pl.coderslab.charity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "User name is required")
    @Size(min = 3, max = 50)
    private String userName;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;

    @Email(message = "Must be a proper email")
    @NotEmpty(message = "Email is required")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Role> roles;





}
