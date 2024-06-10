package upc.edu.LoggyAPI.user.service;

import upc.edu.LoggyAPI.user.model.Profile;
import upc.edu.LoggyAPI.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User addProfileToUser(Long id, Profile profile);
    User editProfileToUser(Long id, Profile profile);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    Boolean deleteUser(Long id);
}
