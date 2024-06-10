package upc.edu.LoggyAPI.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.user.model.Profile;
import upc.edu.LoggyAPI.user.model.User;
import upc.edu.LoggyAPI.user.repository.ProfileRepository;
import upc.edu.LoggyAPI.user.service.UserService;
import upc.edu.LoggyAPI.user.repository.UserRepository;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User createUser(User user) {
        validateUser(user);
        existUserByUserName(user);
        user.setProfile(null);
        return userRepository.save(user);
    }

    @Override
    public User addProfileToUser(Long id, Profile profile) {
        existUserById(id);
        existProfileByIdUser(id);
        validateProfile(profile);
        existProfileByFirstNameAndLastName(profile);
        existProfileByEmail(profile);
        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setProfile(profile);
        return userRepository.save(userToUpdate);
    }

    @Override
    public User editProfileToUser(Long id, Profile profile) {
        existUserById(id);
        validateProfile(profile);
        existProfileByFirstNameAndLastName(profile);
        existProfileByEmail(profile);
        User userToUpdate = userRepository.findById(id).get();
        userToUpdate.setProfile(profile);
        return userRepository.save(userToUpdate);
    }

    @Override
    public User getUserById(Long id) {
        existUserById(id);
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()) {
            throw new ResourceNotFoundException("Unregistered users");
        }
        return allUsers;
    }

    @Override
    public User updateUser(Long id, User user) {
        existUserById(id);
        validateUser(user);
        User userToUpdate = userRepository.findById(id).get();
        validateUserName(userToUpdate, user);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        return userRepository.save(userToUpdate);
    }

    private void validateUserName(User existingUser, User user) {
        // Comprueba si el nombre de usuario ha cambiado y si el nuevo nombre de usuario ya existe
        if (!existingUser.getUsername().equalsIgnoreCase(user.getUsername()) && userRepository.existsByUsernameIgnoreCase(user.getUsername())) {
            throw new IllegalArgumentException(String.format("User name %s already exists", user.getUsername()));
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        existUserById(id);
        userRepository.deleteById(id);
        return true;
    }

    private void validateUser(User user) {
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("User name is required");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("User password is required");
        }
        if(user.getRole() == null || user.getRole().isEmpty()){
            throw new IllegalArgumentException("User profile is required");
        }
    }

    private void existUserByUserName(User user) {
        if(userRepository.existsByUsernameIgnoreCase(user.getUsername())){
            throw new IllegalArgumentException(String.format("User name %s already exists", user.getUsername()));
        }
    }

    private void existUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException(String.format("User id %s not found", id));
        }
    }
    private void validateProfile(Profile profile) {
        if(profile.getFirstname() == null || profile.getFirstname().isEmpty()){
            throw new IllegalArgumentException("Profile first name is required");
        }
        if(profile.getLastname() == null || profile.getLastname().isEmpty()){
            throw new IllegalArgumentException("Profile last name is required");
        }
        if(profile.getEmail() == null || profile.getEmail().isEmpty()){
            throw new IllegalArgumentException("Profile email is required");
        }
        if(profile.getGenre() == null || profile.getGenre().isEmpty()){
            throw new IllegalArgumentException("Profile genre is required");
        }
        if(profile.getBirthdate() == null){
            throw new IllegalArgumentException("Profile birth date is required");
        }
        if(profile.getAddress() == null || profile.getAddress().isEmpty()){
            throw new IllegalArgumentException("Profile address is required");
        }
    }
    private void existProfileByIdUser(Long id) {
        if(userRepository.findById(id).get().getProfile() != null){
            throw new IllegalArgumentException(String.format("User id %s already has a profile", id));
        }
    }
    private void existProfileByFirstNameAndLastName(Profile profile) {
        if(profileRepository.existsByFirstnameAndLastnameIgnoreCase(profile.getFirstname(), profile.getLastname())){
            throw new IllegalArgumentException(String.format("Profile %s %s already exists", profile.getFirstname(), profile.getLastname()));
        }
    }
    private void existProfileByEmail(Profile profile) {
        if(profileRepository.existsByEmail(profile.getEmail())){
            throw new IllegalArgumentException(String.format("Profile email %s already exists", profile.getEmail()));
        }
    }
}
