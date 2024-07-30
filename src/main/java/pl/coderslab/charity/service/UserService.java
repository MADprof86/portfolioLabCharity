package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
import pl.coderslab.charity.exceptions.PasswordMismatchException;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) throws DataNotFoundInDatabaseException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new DataNotFoundInDatabaseException("No User with this email found");

    }
    public User getUserById(Long id) throws DataNotFoundInDatabaseException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return  optionalUser.get();
        }
        throw new DataNotFoundInDatabaseException("No user found");

    }
    public User saveNewUser(User user, String password2) throws EmailUsedException, DataNotFoundInDatabaseException, PasswordMismatchException {
        if(!user.getPassword().equals(password2)){
            throw new PasswordMismatchException("Passwords do not match");
        }

        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailUsedException("User with this email exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> role = getRoles("USER_ROLE");
        user.setRoles(role);
        return userRepository.save(user);

    }
    public void deleteUser(Long id  ) throws DataNotFoundInDatabaseException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent() ){
            userRepository.delete(optionalUser.get());
        }
        throw new DataNotFoundInDatabaseException("User cannot be found ");
    }


    public User updateUser(User user, String password2) throws PasswordMismatchException,
            EmailUsedException,
            DataNotFoundInDatabaseException {
        if(user.getPassword() !=null && !user.getPassword().equals(password2)){
            throw new PasswordMismatchException("Passwords must match");
        }

        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(!optionalUser.isPresent()){
            throw new DataNotFoundInDatabaseException("No user with email found");
        }

        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new EmailUsedException("An account with this email exists");
        }

        if(optionalUser.isPresent() && user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        else {
            user.setPassword(optionalUser.get().getPassword());
        }


        return userRepository.save(user);


    }
    public void validatePassword(User user) throws DataNotFoundInDatabaseException, PasswordMismatchException {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if(!optionalUser.isPresent()){
            throw new DataNotFoundInDatabaseException("User with this email does not exist");
        }
        if(!passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())){
            throw new PasswordMismatchException("Wrong password");
        }
    }

    private Set<Role> getRoles(String roleName) throws DataNotFoundInDatabaseException {
        Set<Role> roles = new HashSet<>();
        String roleType = "";
        switch (roleName){
            case "ROLE_ADMIN" :
                roleType = "ROLE_ADMIN";
            default: roleType = "ROLE_USER";
        }
        Role userRole = roleRepository.findByName(roleType);
        if(userRole == null){
            throw new DataNotFoundInDatabaseException("User ROLE not found");
        }
        roles.add(userRole);
        return roles;
    }

}
