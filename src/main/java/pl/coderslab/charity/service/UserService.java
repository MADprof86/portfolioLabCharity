package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
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
    private RoleRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) throws DataNotFoundInDatabaseException {
        User optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser != null) {
            return optionalUser;
        } else {
            throw new DataNotFoundInDatabaseException("No User with this email found");
        }
    }
    public User getUserById(Long id) throws DataNotFoundInDatabaseException{
        User optionalUser = userRepository.findAllById(id);
        if(optionalUser != null) {
            return  optionalUser;
        } else {
            throw new DataNotFoundInDatabaseException("No user found");
        }
    }
    public User saveNewUser(User user) throws EmailUsedException,DataNotFoundInDatabaseException{
        User oprionaluser = userRepository.findUserByEmail(user.getEmail());
        if(oprionaluser != null){
            throw new EmailUsedException("User with this email exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = getRoles();

        user.setRoles(roles);
        return userRepository.save(user);


    }
    public void deleteUser(Long id  ) throws DataNotFoundInDatabaseException {
        User optionaUser = userRepository.findAllById(id);
        if(optionaUser != null){
            userRepository.delete(optionaUser);
        }
        else {
            throw new DataNotFoundInDatabaseException("User cannot be found ");
        }
    }

    public User updateUser(User user) throws DataNotFoundInDatabaseException, EmailUsedException {
        User optionalUser = userRepository.findUserByEmail(user.getEmail());
        if(optionalUser == null){
          return saveNewUser(user);
        }
        else if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        else {
            user.setPassword(optionalUser.getPassword());
        }
        return userRepository.save(user);
    }

    private Set<Role> getRoles() throws DataNotFoundInDatabaseException {
        Set<Role> roles = new HashSet<>();
        Role userRole = repository.findByName("USER");
        if(userRole == null){
            throw new DataNotFoundInDatabaseException("User ROLE not found");
        }
        roles.add(userRole);
        return roles;
    }

}
